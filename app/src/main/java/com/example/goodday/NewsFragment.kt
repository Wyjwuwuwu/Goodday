package com.example.goodday

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class NewsFragment : Fragment() {

//    private lateinit var newsPicsAdapter: NewsPicsAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = NewsPicAdapter(childFragmentManager,lifecycle)
        viewPager = view.findViewById<ViewPager2>(R.id.view_pager)
        viewPager.setUserInputEnabled(false);
        viewPager.adapter = adapter
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        TabLayoutMediator(tabLayout,viewPager){tab,position->
            when(position){
                0->{
                    tab.text ="Articles"
                }
                1->{
                    tab.text="Videos"
                }
                2->{
                    tab.text="Health Care"
                }

            }
        }.attach()
//        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
//        newsPicsAdapter = NewsPicsAdapter(this)
//        viewPager = view.findViewById(R.id.view_pager)
//        viewPager.adapter = newsPicsAdapter
    }

}
//class NewsPicsAdapter(fragment: Fragment): FragmentStateAdapter(fragment){
//    override fun getItemCount(): Int  = 100
//
//    override fun createFragment(position: Int): Fragment {
//        when (position){
//            0 -> {
//                return NewsPicOneFragment()}
//            1 -> {return NewsPicTwoFragment()}
//            2 -> {return NewsPicThreeFragment()}
//            else ->{return NewsPicOneFragment()}
//        }
//    }
//}
