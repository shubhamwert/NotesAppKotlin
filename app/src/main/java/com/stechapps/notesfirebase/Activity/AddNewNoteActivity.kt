package com.stechapps.notesfirebase.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.stechapps.notesfirebase.R
import kotlinx.android.synthetic.main.activity_add_new_note.*
import kotlin.collections.HashMap

class AddNewNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_note)
        bt_addNote.setOnClickListener { 
            val Head=ed_NoteHeading.text.toString()
            val Descrip=ed_NotesDescription.text.toString()
            val Abst=ed_abstractNote.text.toString()
            
            val db :FirebaseFirestore= FirebaseFirestore.getInstance()
            
            val user =HashMap<String,Any>()
            user["Heading"] = Head
            user["Description"] = Descrip
            user["abs"] = Abst
            val mAuth=FirebaseAuth.getInstance()
            db.collection("Users").document(mAuth.currentUser?.email.toString()).collection("Notes").document(Head).set(user).addOnSuccessListener {
                Toast.makeText(applicationContext,"Added note",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(applicationContext,"something went wrong",Toast.LENGTH_SHORT).show()

            }  
            finish()
        }
    }
}
