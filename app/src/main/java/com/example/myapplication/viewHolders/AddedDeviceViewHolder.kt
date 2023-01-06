package com.example.myapplication.viewHolders

import android.util.Log
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Scanner
import com.example.myapplication.data.AddedDevice
import com.example.myapplication.data.ScannedDevice
import com.example.myapplication.databinding.AddedDeviceLayoutBinding
import com.example.myapplication.databinding.ScannedDeviceLayoutBinding
import com.example.myapplication.mainFragments.DevicesFragmentDirections
import com.example.myapplication.mainFragments.MyDevicesFragmentDirections

class AddedDeviceViewHolder(private val binding: AddedDeviceLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(device: AddedDevice) {
        onClick(device)
        setText(device)
    }

    private fun onClick(device: AddedDevice) {
        binding.device.setOnClickListener {
            Log.v("click",device.address)

            val action = MyDevicesFragmentDirections.actionMyDevicesFragmentToSelectedDeviceFragment(device.result)
            Navigation.findNavController(binding.root).navigate(action)

        }
    }



    private fun clearHomeFragment() {
        Scanner.stopBleScan()
        Scanner.clearScanList()
    }


    private fun setText(device: AddedDevice) {
        binding.deviceAddress.text = device.address
        binding.deviceName.text = device.name
        binding.deviceType.text=device.type
    }

}