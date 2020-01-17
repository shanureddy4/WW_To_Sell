package com.example.ww_to_sell

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.beardedhen.androidbootstrap.BootstrapButton
import com.beardedhen.androidbootstrap.BootstrapEditText

class predictionfragment4 : Fragment(){
    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var f5=predictionfragment5()
        val view: View = inflater.inflate(R.layout.predictfragment4, container, false)
        var getting4:ArrayList<String>? = arguments!!.getStringArrayList("temperature")
//        Toast.makeText(context,getting4!![2],Toast.LENGTH_SHORT).show()
        var gettxt3 = view.findViewById<BootstrapEditText>(R.id.bootstrapEditText7).text
        var nxtbtn= view.findViewById<BootstrapButton>(R.id.next)
        nxtbtn.setOnClickListener {
            getting4!!.add(gettxt3.toString())
            var bundle = Bundle()
            bundle.putStringArrayList("weather",getting4)
            f5.arguments = bundle
            var fragmentTransaction=activity!!.supportFragmentManager.beginTransaction().replace(R.id.container,f5).addToBackStack("tag3").commit()
        }
        return view
    }

}