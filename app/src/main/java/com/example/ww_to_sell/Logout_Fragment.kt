package com.example.ww_to_sell

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class Logout_Fragment : Fragment(){
    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FirebaseAuth.getInstance().signOut()
        Toast.makeText(inflater.context,"Logout Success",Toast.LENGTH_LONG).show()
        startActivity(Intent(inflater.context,MainActivity::class.java))
        return inflater.inflate(R.layout.activity_main,container,false)
    }

}