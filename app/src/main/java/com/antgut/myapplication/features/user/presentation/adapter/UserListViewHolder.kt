package com.antgut.myapplication.features.user.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.antgut.myapplication.databinding.ViewItemAlbumBinding
import com.antgut.myapplication.features.album.domain.Album
import com.antgut.myapplication.features.user.domain.User

class UserListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(user: User, itemClick: ((Int) -> Unit)?) {
        val binding = ViewItemUserBinding.bind(view)
        binding
        view.setOnClickListener {
            itemClick?.invoke(album.id)
        }
    }

}