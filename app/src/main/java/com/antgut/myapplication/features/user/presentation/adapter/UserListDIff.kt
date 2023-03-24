package com.antgut.myapplication.features.user.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.antgut.myapplication.features.album.domain.Album
import com.antgut.myapplication.features.user.domain.User

class UserListDiff : DiffUtil.ItemCallback<User>(){

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}