package com.example.appnews.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.appnews.presentation.main.business.BusinessFragment
import com.example.appnews.presentation.main.entertainment.EntertainmentFragment
import com.example.appnews.presentation.main.health.HealthFragment
import com.example.appnews.presentation.main.science.ScienceFragment
import com.example.appnews.presentation.main.sport.SportFragment
import com.example.appnews.presentation.main.technology.TechnologyFragment
import com.example.appnews.utils.CARD_ITEM_SIZE


class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = CARD_ITEM_SIZE

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> EntertainmentFragment()
            1 -> BusinessFragment()
            2 -> HealthFragment()
            3 -> TechnologyFragment()
            4-> ScienceFragment()
            else -> SportFragment()
        }
    }
}