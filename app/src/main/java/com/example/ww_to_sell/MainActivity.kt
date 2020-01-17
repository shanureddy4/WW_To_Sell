package com.example.ww_to_sell

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.beardedhen.androidbootstrap.BootstrapButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_signup.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        var login=findViewById<BootstrapButton>(R.id.login)
        login.setOnClickListener{
            doLogin()

          //  setContentView(R.layout.fragment_home)
    }
        var signup=findViewById<BootstrapButton>(R.id.signup)
        signup.setOnClickListener {
            var it3=Intent(baseContext,com.example.ww_to_sell.signup::class.java)
            startActivity(it3)
            finish()
        }

    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun doLogin()
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
        auth.signInWithEmailAndPassword(username.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val user = auth.currentUser
                    updateUI(user)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

                // ...
            }
    }
    fun updateUI(currentUser: FirebaseUser?){
        if (currentUser != null)
        {
            if (currentUser.isEmailVerified)
            {
                Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show()
                val it= Intent(baseContext,Home_page::class.java)

                startActivity(it)
                finish()
            }
            else
            {
                Toast.makeText(this,"Please verify your mail first",Toast.LENGTH_SHORT).show()
            }

        }
        else
        {
            Toast.makeText(this,"Login failed",Toast.LENGTH_SHORT).show()
        }
    }



}
