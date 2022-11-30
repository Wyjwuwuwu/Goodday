package com.example.goodday

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView


class ProfileFragment : Fragment() {

    lateinit var drawerLayout : DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)

        drawerLayout = rootView.findViewById(R.id.drawer_layout)
        navigationView = rootView.findViewById(R.id.nav_view)
        toolbar = rootView.findViewById(R.id.toolbar)
        val btn_setting = rootView.findViewById<AppCompatImageButton>(R.id.btn_setting)
        val btn_notification = rootView.findViewById<AppCompatImageButton>(R.id.btn_notification)

        //val activity = activity as AppCompatActivity
        //activity.setSupportActionBar(toolbar);

        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar as Toolbar?)
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayShowTitleEnabled(false);

        val menu = navigationView.menu
        menu.findItem(R.id.nav_logout).setVisible(true)

        navigationView.bringToFront()
        val toggle = ActionBarDrawerToggle(
            activity,
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
                R.id.nav_logbook -> {
                    val intent = Intent (activity, LogbookView::class.java)
                    activity?.startActivity(intent)
                }
                R.id.nav_setting -> {
                    val intent = Intent(activity, SettingActivity::class.java)
                    activity?.startActivity(intent)
                }
                R.id.nav_edit -> {}
                R.id.nav_logout -> {
                    confirm_logout()
                }
                R.id.nav_share -> {}
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        btn_setting.setOnClickListener{
            val intent = Intent (activity, SettingActivity::class.java)
            activity?.startActivity(intent)
        }

        btn_notification.setOnClickListener{
            val intent = Intent (activity, HealthAlertActivity::class.java)
            activity?.startActivity(intent)
        }

        // Inflate the layout for this fragment
        return rootView
    }


    fun confirm_logout() {
        val builder = AlertDialog.Builder(requireContext())
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