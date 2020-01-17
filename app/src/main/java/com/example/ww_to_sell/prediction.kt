package com.example.ww_to_sell

import android.app.PendingIntent.getActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ViewFlipper
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.android.synthetic.main.activity_prediction.*
import org.apache.poi.ss.formula.functions.T



class prediction : AppCompatActivity() {
   private var f1=predictionfragment1()
    lateinit var flipper :ViewFlipper


var fm=supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prediction)
        fm.beginTransaction().replace(R.id.container,f1 ).commit()
        var images = arrayOf(R.drawable.slide1,R.drawable.slide2,R.drawable.slide3)
         flipper = findViewById<ViewFlipper>(R.id.flipper)
        for (im in images)

        {
            flipper(im)
        }

    }
    fun flipper(image:Int)
    {
        var imageview = ImageView(this)
        imageview.setBackgroundResource(image)
        flipper.addView(imageview)
        flipper.flipInterval=4000
        flipper.isAutoStart =true
        flipper.setInAnimation(this,android.R.anim.slide_in_left)
        flipper.setOutAnimation(this,android.R.anim.slide_out_right)


    }



}
