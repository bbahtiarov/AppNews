package com.example.appnews.presentation.navigationDrawer.settings

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.appnews.R
import com.example.appnews.utils.PreferenceHelper
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment : Fragment(R.layout.settings_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            switchCompat.isChecked = true

        switchCompat.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                PreferenceHelper.getInstance().setBoolean("theme", false)
                requireActivity().recreate()
            } else {
                PreferenceHelper.getInstance().setBoolean("theme", true)
                requireActivity().recreate()
            }
        }

    }
}