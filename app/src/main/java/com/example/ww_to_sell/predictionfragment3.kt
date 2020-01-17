package com.example.ww_to_sell

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.beardedhen.androidbootstrap.BootstrapButton
import com.beardedhen.androidbootstrap.BootstrapEditText

class predictionfragment3 : Fragment(){
    var f4=predictionfragment4()
    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.predictfragment3, container, false)
        var getting3:ArrayList<String>? = arguments?.getStringArrayList("month")
        var gettext2=view.findViewById<BootstrapEditText>(R.id.bootstrapEditText7).text
        //Toast.makeText(inflater.context,getting3!![1], Toast.LENGTH_SHORT).show()
        var nxtbtn= view.findViewById<BootstrapButton>(R.id.next)
        nxtbtn.setOnClickListener {
            getting3!!.add(gettext2.toString())
            var bundle=Bundle()
            bundle.putStringArrayList("temperature",getting3)
            f4.arguments =bundle
            var fragmentTransaction=activity!!.supportFragmentManager.beginTransaction().replace(R.id.container,f4).addToBackStack("tag3").commit()
        }
        return view
    }

}