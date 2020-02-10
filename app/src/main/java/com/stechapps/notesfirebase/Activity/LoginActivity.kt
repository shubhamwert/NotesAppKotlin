package com.stechapps.notesfirebase.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.stechapps.notesfirebase.MainActivity
import com.stechapps.notesfirebase.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

  private lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()

    }

    fun btLogin(view: View) {
        if(!btsignswitch.isChecked){
        Toast.makeText(
            this@LoginActivity, "Authentication .",
            Toast.LENGTH_SHORT
        ).show()

        mAuth.signInWithEmailAndPassword(tvemail.text.toString(), tvpassword.text.toString())
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful) { // Sign in success, update UI with the signed-in user's information
                    startActivity(Intent(this,MainActivity::class.java))
                } else { // If sign in fails, display a message to the user.
                    Toast.makeText(
                        this@LoginActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }
    else{
            Toast.makeText(
                this@LoginActivity, "Creating .",
                Toast.LENGTH_SHORT
            ).show()
            mAuth.createUserWithEmailAndPassword(tvemail.text.toString(), tvpassword.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        btsignswitch.isChecked=false
                        finish()

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "register Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }

                }
        }
    }


}
