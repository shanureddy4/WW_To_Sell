package com.example.ww_to_sell

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.beardedhen.androidbootstrap.BootstrapButton
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class Home_Fragment : Fragment(){
    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_home, container, false)


        view.predict.setOnClickListener {view ->
          var it=Intent(context,prediction::class.java)
            startActivity(it)

        }




        return view

        //return inflater.inflate(R.layout.fragment_home,container,false)
    }

}