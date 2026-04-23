package com.example.advancedwealthtracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.advancedwealthtracker.databinding.ActivityMainBinding
import com.example.advancedwealthtracker.ui.InputFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val wealthManager = WealthManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ggLiViewPager.adapter = MainPagerAdapter(this)
        binding.ggLiViewPager.offscreenPageLimit = 3
        binding.ggLiViewPager.isUserInputEnabled = true
        binding.ggLiViewPager.orientation = if (wealthManager.isSurnameStartsWithVowel()) {
            ViewPager2.ORIENTATION_HORIZONTAL
        } else {
            ViewPager2.ORIENTATION_VERTICAL
        }

        TabLayoutMediator(binding.ggLiTabLayout, binding.ggLiViewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.gg_li_tab_input)
                1 -> getString(R.string.gg_li_tab_analytics)
                else -> getString(R.string.gg_li_tab_profile)
            }
        }.attach()

        supportFragmentManager.setFragmentResultListener(
            InputFragment.REQUEST_KEY_NAVIGATION,
            this
        ) { _, bundle ->
            val targetPage = bundle.getInt(InputFragment.BUNDLE_KEY_TARGET_PAGE, 0)
            binding.ggLiViewPager.currentItem = targetPage
        }
    }
}