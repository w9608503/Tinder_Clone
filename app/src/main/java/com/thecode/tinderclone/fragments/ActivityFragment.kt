package com.thecode.tinderclone.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.thecode.tinderclone.R
import com.thecode.tinderclone.adapters.ViewPagerAdapter

/**
 * A simple [Fragment] subclass.
 */
class ActivityFragment : Fragment(), View.OnClickListener, OnPageChangeListener {
    private var mContext: Context? = null
    private lateinit var viewPager: ViewPager
    private lateinit var rootLayout: View
    private lateinit var chatText: TextView
    private lateinit var feedText: TextView
    private lateinit var chatLayout: LinearLayout
    private lateinit var feedLayout: LinearLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootLayout = inflater.inflate(R.layout.fragment_activity, container, false)
        mContext = context
        chatLayout = rootLayout.findViewById(R.id.layout_chat)
        feedLayout = rootLayout.findViewById(R.id.layout_feed)
        chatText = rootLayout.findViewById(R.id.text_chat)
        feedText = rootLayout.findViewById(R.id.text_feed)
        val fragList = ArrayList<Fragment>()
        fragList.add(ChatFragment())
        fragList.add(FeedFragment())
        val pagerAdapter = ViewPagerAdapter(fragList, requireActivity().supportFragmentManager)
        viewPager = rootLayout.findViewById(R.id.view_pager_frag)
        viewPager.setAdapter(pagerAdapter)
        viewPager.addOnPageChangeListener(this)
        chatLayout.setOnClickListener(this)
        feedLayout.setOnClickListener(this)
        return rootLayout
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.layout_chat -> {
                viewPager!!.currentItem = 0
                chatText!!.setTextColor(resources.getColor(R.color.colorPrimary))
                feedText!!.setTextColor(resources.getColor(R.color.light_gray))
            }

            R.id.layout_feed -> {
                viewPager!!.currentItem = 1
                chatText!!.setTextColor(resources.getColor(R.color.light_gray))
                feedText!!.setTextColor(resources.getColor(R.color.colorPrimary))
            }
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
    override fun onPageSelected(position: Int) {
        when (position) {
            0 -> {
                chatText!!.setTextColor(resources.getColor(R.color.colorPrimary))
                feedText!!.setTextColor(resources.getColor(R.color.light_gray))
            }

            1 -> {
                chatText!!.setTextColor(resources.getColor(R.color.light_gray))
                feedText!!.setTextColor(resources.getColor(R.color.colorPrimary))
            }
        }
    }

    override fun onPageScrollStateChanged(state: Int) {}
}