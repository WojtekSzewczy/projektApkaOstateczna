package com.example.myapplication.Adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.databaseHandlers.models.MyRoom
import com.example.myapplication.databaseHandlers.models.User
import com.example.myapplication.databinding.RoomLayoutBinding
import com.example.myapplication.databinding.UserLayoutBinding
import com.example.myapplication.viewHolders.RoomViewHolder
import com.example.myapplication.viewHolders.UserViewHolder

class UsersAdapter : ListAdapter<User, UserViewHolder>(UserCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserLayoutBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val device = getItem(position)
        holder.bind(device)
    }
}

class UserCallback : DiffUtil.ItemCallback<User>() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.UserID == newItem.UserID
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}