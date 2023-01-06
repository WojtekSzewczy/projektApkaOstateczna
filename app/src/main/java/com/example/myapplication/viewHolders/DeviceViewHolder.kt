package com.example.myapplication.viewHolders

import android.util.Log
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Scanner
import com.example.myapplication.data.ScannedDevice
import com.example.myapplication.databinding.ScannedDeviceLayoutBinding
import com.example.myapplication.mainFragments.DevicesFragmentDirections

class DeviceViewHolder(private val binding: ScannedDeviceLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(device: ScannedDevice) {
        onClick(device)
        setText(device)
    }

    private fun onClick(device: ScannedDevice) {
            binding.device.setOnClickListener {
                Log.v("click",device.address)
                val action = DevicesFragmentDirections.actionDevicesFragmentToNewDeviceFragment(device.result)
                Navigation.findNavController(binding.root).navigate(action)
                clearHomeFragment()


            }
    }



    private fun clearHomeFragment() {
        Scanner.stopBleScan()
        Scanner.clearScanList()
    }


    private fun setText(device: ScannedDevice) {
        binding.deviceAddress.text = device.address
        binding.deviceName.text = device.name
    }

}