package com.antgut.myapplication.features.photo.presentiaton.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import com.antgut.myapplication.R
import com.antgut.myapplication.features.photo.domain.Photo

class PhotoListAdapter(val onLongClick: (Photo) -> Unit) : ListAdapter<Photo, PhotoListViewHolder>
    (AsyncDifferConfig.Builder(PhotoListDiff()).build()) {
    private var itemClick: ((Int) -> Unit)? = null

    fun setOnClickItem(itemClick: (Int) -> Unit) {
        this.itemClick = itemClick
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_item_photo, parent, false)
        return PhotoListViewHolder(view) { position -> onLongClick(currentList[position]) }
    }

    override fun onBindViewHolder(holder: PhotoListViewHolder, position: Int) {
        holder.bind(currentList[position], itemClick)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

}