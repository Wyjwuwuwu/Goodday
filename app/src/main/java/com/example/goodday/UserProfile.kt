package com.example.goodday

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class UserProfile : AppCompatActivity() {


    lateinit var drawerLayout : DrawerLayout
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        //drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);

        val menu = navigationView.menu
        menu.findItem(R.id.nav_logout).setVisible(true)

        navigationView.bringToFront()
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_index -> {}
                R.id.nav_record -> {
                }
                R.id.nav_blood_stand -> {
                }
                R.id.nav_history -> {
                }
                R.id.nav_treatment -> {
                }
                R.id.nav_logbook -> {}
                R.id.nav_setting -> {}
                R.id.nav_edit -> {}
                R.id.nav_logout -> {
                    confirm_logout()
                }
                R.id.nav_share -> Toast.makeText(
                    applicationContext,
                    "shared successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun confirm_logout() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle("Logout")
        builder.setMessage("Are you sure you want to logout?")
        builder.setPositiveButton(
            "Confirm"
        ) { dialog, which -> }
        builder.setNegativeButton(
            android.R.string.cancel
        ) { dialog, which -> }
        val dialog = builder.create()
        dialog.show()
    }

}