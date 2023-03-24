package com.antgut.myapplication.features.user.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.antgut.myapplication.databinding.ViewItemUserBinding
import com.antgut.myapplication.features.user.domain.User

class UserListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(user: User, itemClick: ((Int) -> Unit)?) {
        val binding = ViewItemUserBinding.bind(view)
        binding.labelUserName.text = user.name
        binding.labelUserUsername.text = user.username
        binding.labelUserEmail.text = user.email
        view.setOnClickListener {
            itemClick?.invoke(user.id)
        }
    }

}