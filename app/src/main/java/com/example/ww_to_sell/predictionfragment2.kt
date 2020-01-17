package com.example.ww_to_sell

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.beardedhen.androidbootstrap.BootstrapButton
import com.beardedhen.androidbootstrap.BootstrapEditText

class predictionfragment2 : Fragment(){
    var f3=predictionfragment3()
    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.predictfragment2, container, false)
        var getext = view.findViewById<BootstrapEditText>(R.id.bootstrapEditText7).text
        var nxtbtn=view.findViewById<BootstrapButton>(R.id.next)
        var getting2:ArrayList<String?> = arrayListOf(arguments?.getString("selection"))

        //Toast.makeText(context,getting2[0],Toast.LENGTH_SHORT).show()
        nxtbtn.setOnClickListener {
            getting2.add(getext.toString())
            var bundle=Bundle()
            bundle.putStringArrayList("month",getting2)
            f3.arguments =bundle
            var fragmentTransaction=activity!!.supportFragmentManager.beginTransaction().replace(R.id.container,f3).addToBackStack("tag3").commit()
        }
        return view
    }

}