package com.example.myapplication.viewHolders

import android.graphics.Color
import android.util.Log
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainApplication
import com.example.myapplication.Scanner
import com.example.myapplication.data.ScannedDevice
import com.example.myapplication.databaseHandlers.models.Device
import com.example.myapplication.databinding.DeviceInRoomLayoutBinding
import com.example.myapplication.databinding.ScannedDeviceLayoutBinding
import com.example.myapplication.mainFragments.ScanFragmentDirections

class DeviceInRoomViewHolder(private val binding: DeviceInRoomLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(device: Device) {
        setText(device)
    }



    private fun setText(device: Device) {

        binding.deviceAddress.text = device.MACaddress
        binding.deviceName.text = device.description
    }

}