package com.antgut.myapplication.features.album.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.antgut.myapplication.databinding.ViewItemAlbumBinding
import com.antgut.myapplication.features.album.domain.Album

class AlbumListViewHolder(private val view: View, onLongClick: (Int) -> Unit) :
    RecyclerView.ViewHolder(view) {

    init {
        itemView.setOnLongClickListener {
            onLongClick(position)
            true
        }
    }

    fun bind(album: Album, itemClick: ((Int) -> Unit)?) {
        val binding = ViewItemAlbumBinding.bind(view)
        binding.labelTitleAlbum.text = album.title
        view.setOnClickListener {
            itemClick?.invoke(album.id)
        }
    }

}