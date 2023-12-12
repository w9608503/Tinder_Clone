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
import com.thecode.tinderclone.adapters.LikeAdapter
import com.thecode.tinderclone.adapters.MessageListAdapter

import com.thecode.tinderclone.entities.Like
import com.thecode.tinderclone.entities.MessageItem
import java.util.Random

/**
 * A simple [Fragment] subclass.
 */
class ChatFragment : Fragment() {
    private lateinit var rootLayout: View
    private lateinit var messageList: MutableList<MessageItem>
    private lateinit var likeList: MutableList<Like>
    private var mAdapter: MessageListAdapter? = null
    private val messages = arrayOf(
        "Ah d'accord",
        "Juste par habitude en tout cas",
        "Hey!",
        "6946743263",
        "Give me your number, I will call you"
    )
    private val counts = intArrayOf(0, 3, 0, 0, 1)
    private val messagePictures = intArrayOf(
        R.drawable.user_woman_3,
        R.drawable.user_woman_4,
        R.drawable.user_woman_5,
        R.drawable.user_woman_6,
        R.drawable.user_woman_7
    )
    private val likePictures = intArrayOf(R.drawable.user_woman_1, R.drawable.user_woman_2)
    private val messageNames = arrayOf("Fanelle", "Chloe", "Cynthia", "Kate", "Angele")
    private val likeNames = arrayOf("Sophie", "Clara")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootLayout = inflater.inflate(R.layout.fragment_chat, container, false)
        val recyclerView = rootLayout.findViewById<RecyclerView>(R.id.recycler_view_messages)
        messageList = ArrayList()
        mAdapter = MessageListAdapter(requireContext(), messageList)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.adapter = mAdapter
        prepareMessageList()
        prepareContactList()
        val contactAdapter = LikeAdapter(requireContext(), likeList!!)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val recyclerViewContact = rootLayout.findViewById<RecyclerView>(R.id.recycler_view_likes)
        recyclerViewContact.layoutManager = layoutManager
        recyclerViewContact.adapter = contactAdapter
        //new HorizontalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerViewContact));
        return rootLayout
    }

    private fun prepareMessageList() {
        val rand = Random()
        val id = rand.nextInt(100)
        var i: Int
        i = 0
        while (i < 5) {
            val message =
                MessageItem(id, messageNames[i], messages[i], counts[i], messagePictures[i])
            messageList!!.add(message)
            i++
        }
    }

    private fun prepareContactList() {
        likeList = ArrayList()
        val rand = Random()
        val id = rand.nextInt(100)
        var i: Int
        i = 0
        while (i < 2) {
            val like = Like(id, likeNames[i], likePictures[i])
            likeList.add(like)
            i++
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}