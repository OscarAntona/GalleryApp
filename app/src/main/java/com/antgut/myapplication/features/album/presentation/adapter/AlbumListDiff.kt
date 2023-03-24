package com.antgut.myapplication.features.album.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.antgut.myapplication.features.album.domain.Album

class AlbumListDiff : DiffUtil.ItemCallback<Album>() {

    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}