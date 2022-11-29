package com.example.goodday

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class NewsPicAdapter(fragmentManager: FragmentManager,lifecycle:Lifecycle):FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                NewsPicOneFragment()
            }
            1->{
                NewsPicTwoFragment()
            }
            2->{
                NewsPicThreeFragment()
            }

            else->{
                Fragment()
            }
        }

    }

}