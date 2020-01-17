package com.example.ww_to_sell

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.beardedhen.androidbootstrap.BootstrapButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.nav_header.*

class Home_page : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawer:DrawerLayout
    var notification=  Notification_Fragment()
    var soldcrops=SoldCrops_Fragment()
    var logout=Logout_Fragment()
    var prediction=Home_Fragment() //Prediction_Fragment()
    var homefragment=Home_Fragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        drawer=findViewById(R.id.drawer_layout)//getting reference to drawer layout. This will just allow us to draw here and there in layout

        val toolbar=findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)//it is to get 3 lines on the actionbar
        setSupportActionBar(toolbar)
       var toggle= ActionBarDrawerToggle(this,drawer,toolbar, R.string.navigation_open,R.string.navigation_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        var navigationview=findViewById<NavigationView>(R.id.nav_view)
        navigationview.setNavigationItemSelectedListener(this)//here also we can impplement bus code looks awkward


        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, homefragment).commit()//this is just to display the default fragment in Frame layout
        navigationview.setCheckedItem(R.id.nav_notifications)//default selection on navigation menu





    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        when(p0.itemId)
        {
            R.id.nav_notifications -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, notification).commit()
            R.id.nav_soldcrops -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container,soldcrops).commit()
            R.id.nav_prediction -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, prediction).commit()
            R.id.nav_logout -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, logout).commit()
            R.id.nav_home -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container,homefragment).commit()
            else->
                Toast.makeText(this,"Error Occured",Toast.LENGTH_SHORT).show()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
//this is close the nav bar on backbutton pressed
    override fun onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START)
        }
        else {
            super.onBackPressed()
        }
    }
}
