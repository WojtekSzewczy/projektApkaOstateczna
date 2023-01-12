package com.example.myapplication.viewHolders

import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainApplication
import com.example.myapplication.databaseHandlers.models.Device
import com.example.myapplication.databaseHandlers.repositories.ReservationDeviceRepository
import com.example.myapplication.databinding.AddedDeviceLayoutBinding
import com.example.myapplication.mainFragments.MyDevicesFragmentDirections

class AddedDeviceViewHolder(private val binding: AddedDeviceLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @RequiresApi(Build.VERSION_CODES.O)
    val reservationDeviceRepository = ReservationDeviceRepository(MainApplication.appContext)



    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(device: Device) {
        onClick(device)
        setText(device)
        checkReservation(device)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkReservation(device: Device) {

        if(reservationDeviceRepository.checkReservation(device.deviceID)){
            binding.isReserved.text="Urządzenie jest zarezerwowane "
            binding.device.setBackgroundColor(Color.GRAY)
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onClick(device: Device) {
        binding.device.setOnClickListener {
            Log.v("click",device.MACaddress)



            val action = MyDevicesFragmentDirections.actionMyDevicesFragmentToSelectedDeviceFragment(device.deviceID)
            Navigation.findNavController(binding.root).navigate(action)

        }
    }

    private fun setText(device: Device) {
        binding.deviceAddress.text = device.MACaddress
        binding.deviceName.text = device.description
        binding.deviceType.text=device.type
    }

}