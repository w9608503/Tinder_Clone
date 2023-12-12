package com.thecode.tinderclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.thecode.tinderclone.R
import com.thecode.tinderclone.entities.Profile

class UserAdapter(private val userList: List<Profile>, var context: Context) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.userNameTextView.text = user.name
        holder.locationTextView.text = user.location
        holder.age.text = "Age " + user.age

        // Load image using a library like Picasso or Glide
        // For example using Picasso:
        Glide.with(context).load(user.imageUrl).into(holder.userImageView)
        holder.fabLike.setOnClickListener {
            Toast.makeText(
                context,
                "Liked....!",
                Toast.LENGTH_SHORT
            ).show()
        }
        holder.fabSuperLike.setOnClickListener {
            Toast.makeText(
                context,
                "Super Liked....!",
                Toast.LENGTH_SHORT
            ).show()
        }
        holder.fabSkip.setOnClickListener {
            Toast.makeText(
                context,
                "DisLiked....!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userNameTextView: TextView
        val userImageView: ImageView
        val locationTextView: TextView
        val age: TextView
        val fabLike: FloatingActionButton
        val fabSkip: FloatingActionButton
        val fabSuperLike: FloatingActionButton

        init {
            userNameTextView = itemView.findViewById(R.id.userNameTextView)
            userImageView = itemView.findViewById(R.id.userImageView)
            locationTextView = itemView.findViewById(R.id.locationTextView)
            age = itemView.findViewById(R.id.age)
            fabLike = itemView.findViewById(R.id.fabLike)
            fabSkip = itemView.findViewById(R.id.fabSkip)
            fabSuperLike = itemView.findViewById(R.id.fabSuperLike)
        }
    }
}