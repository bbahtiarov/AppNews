package com.example.appnews.presentation.main.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.appnews.R
import com.example.appnews.presentation.adapters.ViewPagerAdapter
import com.example.appnews.utils.APP_ACTIVITY
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(R.layout.main_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val pageAdapter = ViewPagerAdapter(requireActivity())
        view_pager.adapter = pageAdapter
        TabLayoutMediator(tabs, view_pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> {tab.text = "Entertainment"}
                    1 -> {tab.text = "Business"}
                    2 -> {tab.text = "Health"}
                    3 -> {tab.text = "Technology"}
                    4 -> {tab.text = "Science"}
                    5 -> {tab.text = "Sport"}
                }
            }).attach()
    }
}