package com.example.ww_to_sell


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.beardedhen.androidbootstrap.BootstrapButton
import kotlinx.android.synthetic.main.predictfragment5.*
import kotlinx.android.synthetic.main.predictfragment5.view.*
import java.lang.Exception

class predictionfragment5 : Fragment(){
    val pampu = finalresult()
    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.predictfragment5, container, false)
        try {
            var getting5: ArrayList<String>? = arguments?.getStringArrayList("weather")
            if (getting5!![2].toInt() in 22..24) {
                getting5[2] = "verylow"
            } else if (getting5[2].toInt() in 25..27) {
                getting5[2] = "low"
            } else if (getting5[2].toInt() in 28..30) {
                getting5[2] = "medium"
            } else if (getting5[2].toInt() in 31..33) {
                getting5[2] = "high"
            } else if (getting5[2].toInt() > 33) {
                getting5[2] = "veryhigh"
            }
            if (getting5[1].toInt() in 1..3) {
                getting5[1] = "m1"
            } else if (getting5[1].toInt() in 4..6) {
                getting5[1] = "m2"
            } else if (getting5[1].toInt() in 7..9) {
                getting5[1] = "m3"
            } else if (getting5[1].toInt() in 10..12) {
                getting5[1] = "m4"
            }


        view.findViewById<BootstrapButton>(R.id.calculate).setOnClickListener {
//            for (i in getting5) {
////                Toast.makeText(context, i,Toast.LENGTH_LONG).show()
////            }

            var bundle = Bundle()
            bundle.putStringArrayList("finalresult",getting5)
            pampu.arguments = bundle
            var fragmentTransaction=activity!!.supportFragmentManager.beginTransaction().replace(R.id.container,pampu).addToBackStack("tag3").commit()

        }
        }catch (e:Exception)
        {
Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show()
        }
        //textView5.text = arguments?.getString("resultset")



        return view
    }

}
//Todo look to the array list over all fragments and try to puy them clear as they are storing values . if we press back it is not working work on it. (empty the array if we back press)