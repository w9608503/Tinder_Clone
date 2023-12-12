package com.thecode.tinderclone.Firebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.thecode.tinderclone.adapters.AdapterUsers
import com.thecode.tinderclone.entities.User

class UsersActivity : AppCompatActivity() {

    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userAdapter: AdapterUsers
    private val userList = mutableListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.thecode.tinderclone.R.layout.activity_user)

        userRecyclerView = findViewById(com.thecode.tinderclone.R.id.userRecyclerView)
        userAdapter = AdapterUsers()

        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = userAdapter

        retrieveUserList()
    }

    private fun retrieveUserList() {
        val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("/users")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()

                for (userSnapshot in snapshot.children) {
                    val user = userSnapshot.getValue(User::class.java)
                    user?.let {
                        userList.add(user)
                    }
                }

                userAdapter.submitList(userList)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error
            }
        })
    }
}
