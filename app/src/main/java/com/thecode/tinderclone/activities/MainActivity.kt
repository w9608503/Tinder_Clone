package com.thecode.tinderclone.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.thecode.tinderclone.R
import com.thecode.tinderclone.adapters.ViewPagerAdapter
import com.thecode.tinderclone.fragments.AccountFragment
import com.thecode.tinderclone.fragments.ActivityFragment
import com.thecode.tinderclone.fragments.SwipeViewFragment

class MainActivity : AppCompatActivity() , BottomNavigationView.OnNavigationItemSelectedListener {
    private var mContext: Context? = null
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext = this
        val bnv = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val fragList = ArrayList<Fragment>()
        fragList.add(AccountFragment())
        fragList.add(SwipeViewFragment())
        fragList.add(ActivityFragment())
        val pagerAdapter = ViewPagerAdapter(fragList, supportFragmentManager)
        viewPager = findViewById(R.id.view_pager)
        viewPager.setAdapter(pagerAdapter)
        viewPager.setOffscreenPageLimit(3)
        bnv.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.account -> viewPager!!.currentItem = 0
            R.id.fire -> viewPager!!.currentItem = 1
            R.id.chat -> viewPager!!.currentItem = 2
        }
        return true
    }
}