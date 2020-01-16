package com.android.github.ui.users.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.android.github.data.models.UserModel

class UsersPagedAdapter(private val onClick: (UserModel?) -> Unit) : PagedListAdapter<UserModel, UserViewHolder>(FEED_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { onClick.invoke(getItem(position)) }
    }

    companion object {

        private val FEED_DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserModel>() {
            override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean =
                oldItem.id == newItem.id

            override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean =
                oldItem == newItem
        }
    }

}