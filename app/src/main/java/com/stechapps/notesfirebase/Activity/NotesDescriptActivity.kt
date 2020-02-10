package com.stechapps.notesfirebase.Activity

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.stechapps.notesfirebase.R
import kotlinx.android.synthetic.main.activity_notes_descript.*

class NotesDescriptActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_descript)
        val text= intent.extras?.get("Heading").toString()
        val db=FirebaseFirestore.getInstance()
        val mAuth=FirebaseAuth.getInstance()
        db.collection("Users").document(mAuth.currentUser?.email.toString()).collection("Notes").document(text).get().addOnSuccessListener { document ->

            tv_Heading.text=document.get("Heading").toString()
            tv_descipt.text=document.get("Description").toString()
            pb_progress_Bar.visibility= View.GONE
        }



    }
}
