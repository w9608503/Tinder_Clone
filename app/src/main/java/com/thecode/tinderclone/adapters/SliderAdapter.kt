package com.thecode.tinderclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter
import com.thecode.tinderclone.R
import com.thecode.tinderclone.adapters.SliderAdapter.SliderAdapterVH

class SliderAdapter(private val context: Context) : SliderViewAdapter<SliderAdapterVH>() {
    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_layout_slider, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        when (position) {
            0 -> {
                Glide.with(viewHolder.itemView)
                    .load(R.drawable.bolt)
                    .into(viewHolder.imageViewSlider)
                viewHolder.textViewTitle.text = context.resources.getString(R.string.title_slider_1)
                viewHolder.textViewDescription.text =
                    context.resources.getString(R.string.description_slider_1)
            }

            1 -> {
                Glide.with(viewHolder.itemView)
                    .load(R.drawable.ic_like_24dp)
                    .into(viewHolder.imageViewSlider)
                viewHolder.textViewTitle.text = context.resources.getString(R.string.title_slider_2)
                viewHolder.textViewDescription.text =
                    context.resources.getString(R.string.description_slider_2)
            }

            2 -> {
                Glide.with(viewHolder.itemView)
                    .load(R.drawable.ic_location_on_blue_24dp)
                    .into(viewHolder.imageViewSlider)
                viewHolder.textViewTitle.text = context.resources.getString(R.string.title_slider_3)
                viewHolder.textViewDescription.text =
                    context.resources.getString(R.string.description_slider_3)
            }

            3 -> {
                Glide.with(viewHolder.itemView)
                    .load(R.drawable.ic_star_blue_24dp)
                    .into(viewHolder.imageViewSlider)
                viewHolder.textViewTitle.text = context.resources.getString(R.string.title_slider_4)
                viewHolder.textViewDescription.text =
                    context.resources.getString(R.string.description_slider_4)
            }

            4 -> {
                Glide.with(viewHolder.itemView)
                    .load(R.drawable.reverse)
                    .into(viewHolder.imageViewSlider)
                viewHolder.textViewTitle.text = context.resources.getString(R.string.title_slider_5)
                viewHolder.textViewDescription.text =
                    context.resources.getString(R.string.description_slider_5)
            }

            5 -> {
                Glide.with(viewHolder.itemView)
                    .load(R.drawable.ic_star_turquoise_24dp)
                    .into(viewHolder.imageViewSlider)
                viewHolder.textViewTitle.text = context.resources.getString(R.string.title_slider_6)
                viewHolder.textViewDescription.text =
                    context.resources.getString(R.string.description_slider_6)
            }
        }
    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return 6
    }

    inner class SliderAdapterVH(var itemView: View) : ViewHolder(
        itemView
    ) {
        var imageViewSlider: ImageView
        var textViewDescription: TextView
        var textViewTitle: TextView

        init {
            imageViewSlider = itemView.findViewById(R.id.image_slider)
            textViewTitle = itemView.findViewById(R.id.text_slider_title)
            textViewDescription = itemView.findViewById(R.id.text_slider_description)
        }
    }
}