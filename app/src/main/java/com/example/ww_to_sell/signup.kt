package com.example.ww_to_sell

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*

class signup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()
        submit.setOnClickListener {
           signupuseer()
        }
    }
    private fun signupuseer()
    {
        if (username.text.toString().isEmpty())
        {
            username.error="please enter email"
             username.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()){
            username.error = "please enter valid email"
            username.requestFocus()
            return
        }
        if (password.text.toString().isEmpty()){
            password.error = "please enter password"
            return
        }
        auth.createUserWithEmailAndPassword(username.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser

                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this,MainActivity::class.java))
                                finish()
                            }
                        }

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "faild it seems user already exists",
                        Toast.LENGTH_SHORT).show()

                }

                // ...
            }
    }
}
