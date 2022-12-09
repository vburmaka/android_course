package com.example.lesson13retrofit.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lesson13retrofit.R
import com.example.lesson13retrofit.models.GalleryItem

class PhotoAdapter(context: Context, private val galleryItems: List<GalleryItem>) : RecyclerView.Adapter<PhotoAdapter.PhotoHolder>() {
    private val requestManager = Glide.with(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gallery, parent, false)
        return PhotoHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.bind(galleryItems[position])
    }

    override fun getItemCount() = galleryItems.size

   inner class PhotoHolder(view: View) : RecyclerView.ViewHolder(view){
        private var titleTextView: TextView? = null
        private var imageView: ImageView? = null

        init {
            titleTextView = view.findViewById(R.id.galleryTitleTextView)
            imageView = view.findViewById(R.id.galleryImageView)
        }

        fun bind(item: GalleryItem){
            titleTextView?.text = item.title
            loadImage(item.url)
        }

        private fun loadImage(url: String) {
            imageView?.let {
                requestManager.load(url)
                    .centerCrop()
                    .placeholder(R.drawable.ic_baseline_cached_24)
                    .into(it)
            }
        }
    }
}