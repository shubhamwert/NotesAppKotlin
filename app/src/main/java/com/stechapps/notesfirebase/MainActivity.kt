package com.stechapps.notesfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.stechapps.notesfirebase.Activity.AddNewNoteActivity
import com.stechapps.notesfirebase.Activity.LoginActivity
import com.stechapps.notesfirebase.Adapters.NotesListAdapter
import com.stechapps.notesfirebase.models.NotesListModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_notes_descript.*

class MainActivity : AppCompatActivity() {
    lateinit var db:FirebaseFirestore
    lateinit var NoteAdapter:NotesListAdapter
    lateinit var dataset:ArrayList<NotesListModel>
    lateinit var mAuth: FirebaseAuth


    val TAG="Main Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db= FirebaseFirestore.getInstance()
        dataset= ArrayList()
        mAuth= FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        mAuth.addAuthStateListener {user ->
            if (currentUser!=null){
                doSomething()


            }
            else{
                startActivity(Intent(this,LoginActivity::class.java))

            }
        }



    }

    override fun onPostResume() {
        super.onPostResume()


    }

    fun doSomething(){
        val layoutManager = LinearLayoutManager(this)
        rv_notesList.layoutManager=layoutManager
        NoteAdapter= NotesListAdapter(dataset,this)
        rv_notesList.adapter=NoteAdapter
        db.collection("Users").document(mAuth.currentUser?.email.toString()).collection("Notes").addSnapshotListener{snapshot, firebaseFirestoreException ->
            if(firebaseFirestoreException!=null){
                Log.w(TAG, "Listen failed.", firebaseFirestoreException)
                return@addSnapshotListener

            }
            dataset.removeAll(dataset)
            if (snapshot != null ) {
                for (i in snapshot){
                    dataset.add(NotesListModel(i.data.get("Heading").toString(),i.data.get("abs").toString()))
                }
                NoteAdapter.notifyDataSetChanged()
                pb_main.visibility=View.GONE
            } else {
                Log.d(TAG, "Current data: null")
            }

        }
    }

    fun btAddNewNote(view: View?) {
        startActivity(Intent(applicationContext,
            AddNewNoteActivity::class.java))
    }
}
