package com.antgut.myapplication.features.album.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.antgut.myapplication.databinding.ViewItemAlbumBinding
import com.antgut.myapplication.features.album.domain.Album

class AlbumListViewHolder(private val view: View) :
    RecyclerView.ViewHolder(view) {
    fun bind(album: Album, itemClick: ((Int) -> Unit)?, onLongClick: ((Int) -> Unit)?) {
        val binding = ViewItemAlbumBinding.bind(view)
        binding.labelTitleAlbum.text = album.title
        view.setOnClickListener {
            album.id?.let { it1 -> itemClick?.invoke(it1) }

        }
        view.setOnLongClickListener {
            album.id?.let { it1 -> onLongClick?.invoke(it1) }
            true
        }
    }

}