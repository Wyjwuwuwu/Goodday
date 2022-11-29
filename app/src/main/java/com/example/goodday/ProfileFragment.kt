package com.example.goodday

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView


class ProfileFragment : Fragment() {

    lateinit var drawerLayout : DrawerLayout
    lateinit var navigationView: NavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        drawerLayout = rootView.findViewById(R.id.drawer_layout)
        navigationView = rootView.findViewById(R.id.nav_view)
        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)

        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)

        val menu = navigationView.menu
        menu.findItem(R.id.nav_logout).setVisible(true)

        navigationView.bringToFront()
        val toggle = ActionBarDrawerToggle(
            requireActivity(),
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
                R.id.nav_share -> {}
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
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