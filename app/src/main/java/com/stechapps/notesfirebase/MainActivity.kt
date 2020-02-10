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
import com.google.firebase.firestore.FirebaseFirestore
import com.stechapps.notesfirebase.Activity.AddNewNoteActivity
import com.stechapps.notesfirebase.Adapters.NotesListAdapter
import com.stechapps.notesfirebase.models.NotesListModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var db:FirebaseFirestore
    lateinit var NoteAdapter:NotesListAdapter
    lateinit var dataset:ArrayList<NotesListModel>

    val TAG="Main Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db= FirebaseFirestore.getInstance()
        dataset= ArrayList()
        NoteAdapter=NotesListAdapter(dataset,this)
        val layoutManager = LinearLayoutManager(this)
        rv_notesList.layoutManager=layoutManager
        rv_notesList.adapter=NoteAdapter

        var i:Int=0
        db.collection("Notes")
            .get()
            .addOnSuccessListener { result ->
                dataset.removeAll(dataset)
                for (document in result) {
                    dataset.add(NotesListModel(document.get("Heading").toString(),document.get("abs").toString()))

                }

                NoteAdapter.notifyDataSetChanged()

            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }

    }

    override fun onPostResume() {
        super.onPostResume()

        db.collection("Notes")
            .get()
            .addOnSuccessListener { result ->
                dataset.removeAll(dataset)

                for (document in result) {
                    dataset.add(NotesListModel(document.get("Heading").toString(),document.get("abs").toString()))

                }
                NoteAdapter.notifyDataSetChanged()

            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }

    fun btAddNewNote(view: View?) {
        startActivity(Intent(applicationContext,
            AddNewNoteActivity::class.java))
    }
}
