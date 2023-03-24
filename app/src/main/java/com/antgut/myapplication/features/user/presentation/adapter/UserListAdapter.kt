package com.antgut.myapplication.features.user.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import com.antgut.myapplication.R
import com.antgut.myapplication.features.user.domain.User

class UserListAdapter : ListAdapter<User, UserListViewHolder>
    (AsyncDifferConfig.Builder(UserListDiff()).build()) {
    private var itemClick: ((Int) -> Unit)? = null

    fun setOnClickItem(itemClick: (Int) -> Unit) {
        this.itemClick = itemClick
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_item_user, parent, false)
        return UserListViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(currentList[position], itemClick)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}