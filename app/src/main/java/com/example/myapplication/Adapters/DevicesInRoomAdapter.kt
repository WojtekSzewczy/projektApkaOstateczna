package com.example.myapplication.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.data.ScannedDevice
import com.example.myapplication.databaseHandlers.models.Device
import com.example.myapplication.databinding.DeviceInRoomLayoutBinding
import com.example.myapplication.databinding.ScannedDeviceLayoutBinding
import com.example.myapplication.viewHolders.DeviceInRoomViewHolder
import com.example.myapplication.viewHolders.DeviceViewHolder

class DeviceInRoomAdapter : ListAdapter<Device, DeviceInRoomViewHolder>(DeviceInRoomCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceInRoomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DeviceInRoomLayoutBinding.inflate(inflater, parent, false)
        return DeviceInRoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeviceInRoomViewHolder, position: Int) {
        val device = getItem(position)
        holder.bind(device)
    }
}

class DeviceInRoomCallback : DiffUtil.ItemCallback<Device>() {
    override fun areItemsTheSame(oldItem: Device, newItem: Device): Boolean {
        return oldItem.deviceID == newItem.deviceID
    }

    override fun areContentsTheSame(oldItem: Device, newItem: Device): Boolean {
        return oldItem == newItem
    }
}