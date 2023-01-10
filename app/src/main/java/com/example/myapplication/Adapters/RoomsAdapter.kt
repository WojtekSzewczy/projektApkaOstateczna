package com.example.myapplication.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.databaseHandlers.models.MyRoom
import com.example.myapplication.databinding.RoomLayoutBinding
import com.example.myapplication.viewHolders.RoomViewHolder

class RoomsAdapter : ListAdapter<MyRoom, RoomViewHolder>(RoomCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RoomLayoutBinding.inflate(inflater, parent, false)
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val device = getItem(position)
        holder.bind(device)
    }
}

class RoomCallback : DiffUtil.ItemCallback<MyRoom>() {
    override fun areItemsTheSame(oldItem: MyRoom, newItem: MyRoom): Boolean {
        return oldItem.roomID == newItem.roomID
    }

    override fun areContentsTheSame(oldItem: MyRoom, newItem: MyRoom): Boolean {
        return oldItem == newItem
    }
}