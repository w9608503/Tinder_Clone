package com.thecode.tinderclone.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.thecode.tinderclone.R
import com.thecode.tinderclone.Utils.loadProfiles
import com.thecode.tinderclone.adapters.UserAdapter
import com.thecode.tinderclone.entities.Profile

/**
 * A simple [Fragment] subclass.
 */
class SwipeViewFragment : Fragment() {
    private var rootLayout: View? = null
    private lateinit var fabBack: FloatingActionButton
    private lateinit var fabLike: FloatingActionButton
    private lateinit var fabSkip: FloatingActionButton
    private lateinit var fabSuperLike: FloatingActionButton
    private lateinit var fabBoost: FloatingActionButton
    private val mSwipeView: SwipePlaceHolderView? = null
    private var mContext: Context? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootLayout = inflater.inflate(R.layout.fragment_swipe_view, container, false)
        return rootLayout
    }

    private lateinit var userList: MutableList<Profile>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //        mSwipeView = view.findViewById(R.id.swipeView);
        fabBack = view.findViewById(R.id.fabBack)
        fabLike = view.findViewById(R.id.fabLike)
        fabSkip = view.findViewById(R.id.fabSkip)
        fabSuperLike = view.findViewById(R.id.fabSuperLike)
        fabBoost = view.findViewById(R.id.fabBoost)
        userList = ArrayList()
        val recyclerView = view.findViewById<RecyclerView>(R.id.swipeView)
        recyclerView.layoutManager = LinearLayoutManager(context)


        // Add sample data
//        userList.add(new UserModel("John Doe", "https://example.com/image1.jpg", "New York"));
//        userList.add(new UserModel("Alice Smith", "https://example.com/image2.jpg", "London"));
//        // Add more user items as needed...


//        int bottomMargin = Utils.dpToPx(100);
//        Point windowSize = Utils.getDisplaySize(getActivity().getWindowManager());
//        mSwipeView.getBuilder()
//                .setDisplayViewCount(3)
//                .setSwipeDecor(new SwipeDecor()
//                        .setViewWidth(windowSize.x)
//                        .setViewHeight(windowSize.y - bottomMargin)
//                        .setViewGravity(Gravity.TOP)
//                        .setPaddingTop(20)
//                        .setRelativeScale(0.01f)
//                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
//                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view));
//
        for (profile in loadProfiles(
            requireActivity()
        )!!) {
//            Toast.makeText(mContext, ""+profile.getName(), Toast.LENGTH_SHORT).show();
//            mSwipeView.addView(new TinderCard(mContext, profile, mSwipeView));
            userList.add(profile)
            val adapter = context?.let { UserAdapter(userList, it) }
            recyclerView.adapter = adapter
            mContext = activity
        }
        fabSkip.setOnClickListener(View.OnClickListener { v: View? -> animateFab(fabSkip) })
        fabLike.setOnClickListener(View.OnClickListener { v: View? -> animateFab(fabLike) })
        fabBoost.setOnClickListener(View.OnClickListener { v: View? -> animateFab(fabBoost) })
        fabSuperLike.setOnClickListener(View.OnClickListener { v: View? -> animateFab(fabSuperLike) })
        fabBack.setOnClickListener(View.OnClickListener { v: View? -> animateFab(fabBack) })
    }

    private fun animateFab(fab: FloatingActionButton?) {
        fab!!.animate().scaleX(0.7f).setDuration(100)
            .withEndAction { fab.animate().scaleX(1f).scaleY(1f) }
    }
}