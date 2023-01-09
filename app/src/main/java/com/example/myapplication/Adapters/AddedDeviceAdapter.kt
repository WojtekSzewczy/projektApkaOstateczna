package com.example.myapplication.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.data.AddedDevice
import com.example.myapplication.databaseModels.Device
import com.example.myapplication.databinding.AddedDeviceLayoutBinding
import com.example.myapplication.viewHolders.AddedDeviceViewHolder


class AddedDeviceAdapter : ListAdapter<Device, AddedDeviceViewHolder>(AddedDeviceCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddedDeviceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AddedDeviceLayoutBinding.inflate(inflater, parent, false)
        return AddedDeviceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddedDeviceViewHolder, position: Int) {
        val device = getItem(position)
        holder.bind(device)
    }
}

class AddedDeviceCallback : DiffUtil.ItemCallback<Device>() {
    override fun areItemsTheSame(oldItem: Device, newItem: Device): Boolean {
        return oldItem.MACaddress == newItem.MACaddress
    }

    override fun areContentsTheSame(oldItem: Device, newItem: Device): Boolean {
        return oldItem == newItem
    }
}
