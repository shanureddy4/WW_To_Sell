package com.example.ww_to_sell

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.beardedhen.androidbootstrap.BootstrapButton
import kotlinx.android.synthetic.main.activity_prediction.*
import kotlinx.android.synthetic.main.predictfragment1.*
import kotlinx.android.synthetic.main.predictfragment1.view.*

class predictionfragment1 : Fragment(), AdapterView.OnItemSelectedListener{
    override fun onNothingSelected(p0: AdapterView<*>?) {}//spinner method


    var croparray=arrayOf("rice","jower","wheat","tomato","groundnut")
    var f2=predictionfragment2()
    var sub:String =""
    var getting:ArrayList<String>? = arguments?.getStringArrayList("send")

    public override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.predictfragment1, container, false)
            var go= view.findViewById<BootstrapButton>(R.id.gobtn)
            var spinner=  view.findViewById(R.id.spinner)as Spinner

        var adapter: ArrayAdapter<String> = ArrayAdapter<String>(inflater.context ,android.R.layout.simple_dropdown_item_1line,croparray)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.setAdapter(adapter)
        with(spinner)
        {
            onItemSelectedListener=this@predictionfragment1
        }
        go.setOnClickListener {
            var bundle=Bundle()
            bundle.putString("selection",sub)
            f2.arguments=bundle
            var fragmentTransaction=activity!!.supportFragmentManager.beginTransaction().replace(R.id.container,f2).addToBackStack("tag2").commit()

        }


        return view
    }
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {



        if (croparray[p2]=="---select---")
        {
            Toast.makeText(context,"please select crop",Toast.LENGTH_SHORT).show()
        }
        sub= croparray[p2]
       // getting!!.add(croparray[p2])



    }




}