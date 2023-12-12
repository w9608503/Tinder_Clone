package com.thecode.tinderclone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thecode.tinderclone.R
import com.thecode.tinderclone.activities.MainActivity
import com.thecode.tinderclone.adapters.MatchListAdapter
import com.thecode.tinderclone.entities.Match
import java.util.Random

/**
 * A simple [Fragment] subclass.
 */
class FeedFragment : Fragment() {
    lateinit var rootLayout: View
    private lateinit var matchList: MutableList<Match>
    private var mAdapter: MatchListAdapter? = null
    private val matchDates =
        arrayOf("11 Jan. 2020", "26 Dec. 2019", "12 Dec. 2019", "17 Nov. 2019", "06 Oct. 2019")
    private val matchPictures = intArrayOf(
        R.drawable.user_woman_3,
        R.drawable.user_woman_4,
        R.drawable.user_woman_5,
        R.drawable.user_woman_6,
        R.drawable.user_woman_7
    )
    private val matchNames = arrayOf("Fanelle", "Chloe", "Cynthia", "Kate", "Angele")
    private val matchLocations =
        arrayOf("à 3 km", "à 18 km", "à moins d'un kilometre", "à 4 km", "à 6 km")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootLayout = inflater.inflate(R.layout.fragment_feed, container, false)
        val recyclerView = rootLayout.findViewById<RecyclerView>(R.id.recycler_view_matchs)
        matchList = ArrayList()
        mAdapter = MatchListAdapter(requireContext(), matchList)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.adapter = mAdapter
        prepareMatchList()
        return rootLayout
    }

    private fun prepareMatchList() {
        val rand = Random()
        val id = rand.nextInt(100)
        var i: Int
        i = 0
        while (i < 5) {
            val match = Match(id, matchNames[i], matchPictures[i], matchLocations[i], matchDates[i])
            matchList!!.add(match)
            i++
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}