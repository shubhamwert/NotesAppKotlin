package com.stechapps.notesfirebase.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import com.stechapps.notesfirebase.R
import kotlinx.android.synthetic.main.activity_notes_descript.*

class NotesDescriptActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_descript)
        val text= intent.extras?.get("Heading").toString()
        val db=FirebaseFirestore.getInstance()
        db.collection("Notes").document(text).get().addOnSuccessListener {document ->
            checker.text=document.get("Description").toString()
        }
    }
}
