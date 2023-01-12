package com.example.myapplication.viewHolders

import android.graphics.Color
import android.util.Log
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainApplication
import com.example.myapplication.Scanner
import com.example.myapplication.data.ScannedDevice
import com.example.myapplication.databinding.ScannedDeviceLayoutBinding
import com.example.myapplication.mainFragments.ScanFragmentDirections

class DeviceViewHolder(private val binding: ScannedDeviceLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(device: ScannedDevice) {
        onClick(device)
        setText(device)
    }

    private fun onClick(device: ScannedDevice) {
        binding.device.setOnClickListener {
            Log.v("click", device.address)


            if (MainApplication.isAdmin) {
                val action =
                    ScanFragmentDirections.actionDevicesFragmentToNewDeviceFragment(device.result)
                Navigation.findNavController(binding.root).navigate(action)
                clearHomeFragment()
                Scanner.connect(device.result)
                Thread.sleep(2500)
            } else {
                Toast.makeText(
                    MainApplication.appContext,
                    "Only admin can add new device",
                    Toast.LENGTH_SHORT
                ).show()
            }


        }
    }


    private fun clearHomeFragment() {
        Scanner.stopBleScan()
        Scanner.clearScanList()
    }


    private fun setText(device: ScannedDevice) {
        if(device.result.rssi>-90){
            binding.device.setBackgroundColor(Color.GREEN)
        }
        binding.deviceAddress.text = device.address
        binding.deviceName.text = device.name
        binding.rssiTextVIew.text=device.result.rssi.toString()
    }

}