package com.example.advancedwealthtracker

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.advancedwealthtracker.ui.AnalyticsFragment
import com.example.advancedwealthtracker.ui.InputFragment
import com.example.advancedwealthtracker.ui.ProfileFragment

class MainPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> InputFragment()
            1 -> AnalyticsFragment()
            else -> ProfileFragment()
        }
    }
}
