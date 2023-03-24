package com.antgut.myapplication.features.photo.presentiaton.adapter


import androidx.recyclerview.widget.DiffUtil
import com.antgut.myapplication.features.photo.domain.Photo

class PhotoListDiff : DiffUtil.ItemCallback<Photo>() {

    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}