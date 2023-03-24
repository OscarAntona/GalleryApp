package com.antgut.myapplication.features.album.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import com.antgut.myapplication.R
import com.antgut.myapplication.features.album.domain.Album

class AlbumListAdapter : ListAdapter<Album, AlbumListViewHolder>
    (AsyncDifferConfig.Builder(AlbumListDiff()).build()) {
    private var itemClick: ((Int) -> Unit)? = null

    fun setOnClickItem(itemClick: (Int) -> Unit) {
        this.itemClick = itemClick
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_item_album, parent, false)
        return AlbumListViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumListViewHolder, position: Int) {
        holder.bind(currentList[position], itemClick)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

}