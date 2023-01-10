package com.example.myapplication.viewHolders

import android.util.Log
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Scanner
import com.example.myapplication.data.AddedDevice
import com.example.myapplication.data.ScannedDevice
import com.example.myapplication.databaseModels.Device
import com.example.myapplication.databinding.AddedDeviceLayoutBinding
import com.example.myapplication.databinding.ScannedDeviceLayoutBinding
import com.example.myapplication.mainFragments.MyDevicesFragmentDirections

class AddedDeviceViewHolder(private val binding: AddedDeviceLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(device: Device) {
        onClick(device)
        setText(device)
    }

    private fun onClick(device: Device) {
        binding.device.setOnClickListener {
            Log.v("click",device.MACaddress)



            val action = MyDevicesFragmentDirections.actionMyDevicesFragmentToSelectedDeviceFragment(device.id)
            Navigation.findNavController(binding.root).navigate(action)

        }
    }

    private fun setText(device: Device) {
        binding.deviceAddress.text = device.MACaddress
        binding.deviceName.text = device.description
        binding.deviceType.text=device.type
    }

}