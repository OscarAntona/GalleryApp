package com.antgut.myapplication.features.photo.presentiaton.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.antgut.myapplication.app.extensions.loadUrl
import com.antgut.myapplication.databinding.ViewItemPhotoBinding
import com.antgut.myapplication.features.photo.domain.Photo

class PhotoListViewHolder(private val view: View) :
    RecyclerView.ViewHolder(view) {
    fun bind(photo: Photo, itemClick: ((Int) -> Unit)?, onLongClick: ((Int) -> Unit)?) {
        val binding = ViewItemPhotoBinding.bind(view)
        binding.photoThumbnail.loadUrl(photo.thumbnailUrl)
        view.setOnClickListener {
            itemClick?.invoke(photo.id)
        }
        view.setOnLongClickListener {
            onLongClick?.invoke(photo.id)
            true
        }
    }

}