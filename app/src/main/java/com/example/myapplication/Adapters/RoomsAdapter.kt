package com.example.myapplication.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.data.Room
import com.example.myapplication.data.ScannedDevice
import com.example.myapplication.databinding.RoomLayoutBinding
import com.example.myapplication.databinding.ScannedDeviceLayoutBinding
import com.example.myapplication.viewHolders.DeviceViewHolder
import com.example.myapplication.viewHolders.RoomViewHolder

class RoomsAdapter : ListAdapter<Room, RoomViewHolder>(RoomCallback()) {

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

class RoomCallback : DiffUtil.ItemCallback<Room>() {
    override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem == newItem
    }
}