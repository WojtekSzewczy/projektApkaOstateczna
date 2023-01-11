package com.example.myapplication.Adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.databaseHandlers.models.User
import com.example.myapplication.databinding.UserInRoomLayoutBinding
import com.example.myapplication.databinding.UserLayoutBinding
import com.example.myapplication.viewHolders.UserInRoomViewHolder
import com.example.myapplication.viewHolders.UserViewHolder

class UsersInRoomAdapter : ListAdapter<User, UserInRoomViewHolder>(UserInRoomCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserInRoomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserInRoomLayoutBinding.inflate(inflater, parent, false)
        return UserInRoomViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: UserInRoomViewHolder, position: Int) {
        val device = getItem(position)
        holder.bind(device)
    }
}

class UserInRoomCallback : DiffUtil.ItemCallback<User>() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.UserID == newItem.UserID
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}