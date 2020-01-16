package com.android.github.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.github.R
import com.android.github.data.models.UserModel
import com.android.github.ui.utils.CircleTransformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user.view.*

class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {
    private val tvName: TextView = itemView.tvName
    private val ivAvatar: ImageView = itemView.ivAvatar
    fun bind(user: UserModel?) {
        tvName.text = user?.login
        if (user != null && !user.avatarUrl.isNullOrEmpty()) {
                Picasso.get().load(user.avatarUrl).transform(CircleTransformation())
                    .into(ivAvatar)
        }
    }
}