package com.example.myapplication.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.viewHolders.DeviceViewHolder
import com.example.myapplication.data.ScannedDevice
import com.example.myapplication.databinding.ScannedDeviceLayoutBinding


class DeviceAdapter : ListAdapter<ScannedDevice, DeviceViewHolder>(DeviceCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ScannedDeviceLayoutBinding.inflate(inflater, parent, false)
        return DeviceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = getItem(position)
        holder.bind(device)
    }
}

class DeviceCallback : DiffUtil.ItemCallback<ScannedDevice>() {
    override fun areItemsTheSame(oldItem: ScannedDevice, newItem: ScannedDevice): Boolean {
        return oldItem.address == newItem.address
    }

    override fun areContentsTheSame(oldItem: ScannedDevice, newItem: ScannedDevice): Boolean {
        return oldItem == newItem
    }
}
