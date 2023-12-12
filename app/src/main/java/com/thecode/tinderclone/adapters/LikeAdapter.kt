package com.thecode.tinderclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thecode.tinderclone.R
import com.thecode.tinderclone.adapters.LikeAdapter.ContactViewHolder
import com.thecode.tinderclone.entities.Like

class LikeAdapter(private val context: Context, private val likeList: List<Like>) :
    RecyclerView.Adapter<ContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_like_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return likeList.size
    }

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var likeLayout: LinearLayout
        var likeName: TextView
        var likeImage: ImageView

        init {
            likeLayout = itemView.findViewById(R.id.layout_like)
            likeName = itemView.findViewById(R.id.text_like_name)
            likeImage = itemView.findViewById(R.id.circle_image_like)
        }
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = likeList[position]
        holder.likeName.text = item.name
        Glide.with(context)
            .load(item.picture)
            .into(holder.likeImage)
    }
}