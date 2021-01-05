package com.example.appnews


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.appnews.utils.APP_ACTIVITY
import com.example.appnews.utils.PreferenceHelper
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mToolbar: Toolbar
    private lateinit var mToggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPrefHelper()

        setTheme(R.style.AppTheme)

        APP_ACTIVITY = this
        setContentView(R.layout.activity_main)
        initFields()
        navigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }

    private fun initPrefHelper() {
        PreferenceHelper.initialize(this)
        if (!PreferenceHelper.getInstance().getBoolean("theme")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun initFields() {
        mToolbar = main_toolbar
        mToolbar.title = ""
        setSupportActionBar(mToolbar)
        mToggle = ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(mToggle)
        mToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (mToggle.onOptionsItemSelected(item)) return true
        return when (item.itemId) {
            R.id.search_menu -> {
                // Open the search view on the menu item click.
                search_view.openSearch()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}