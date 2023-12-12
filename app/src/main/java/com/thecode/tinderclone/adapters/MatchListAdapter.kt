package com.thecode.tinderclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thecode.tinderclone.R
import com.thecode.tinderclone.entities.Match

class MatchListAdapter(private val context: Context, private val matchList: List<Match>) :
    RecyclerView.Adapter<MatchListAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView
        var date: TextView
        var location: TextView
        var imgProfile: ImageView
        var imgContent: ImageView

        init {
            name = view.findViewById(R.id.text_name)
            date = view.findViewById(R.id.text_date)
            location = view.findViewById(R.id.text_location)
            imgProfile = view.findViewById(R.id.img_profile)
            imgContent = view.findViewById(R.id.img_content)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_layout_match, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = matchList[position]
        holder.name.text = item.name
        holder.date.text = item.date
        holder.location.text = item.location
        Glide.with(context)
            .load(item.picture)
            .into(holder.imgProfile)
        Glide.with(context)
            .load(item.picture)
            .into(holder.imgContent)
    }

    override fun getItemCount(): Int {
        return matchList.size
    }
}