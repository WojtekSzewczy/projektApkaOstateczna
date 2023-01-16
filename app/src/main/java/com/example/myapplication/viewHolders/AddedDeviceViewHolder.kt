package com.example.myapplication.viewHolders

import android.graphics.Color
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainApplication
import com.example.myapplication.databaseHandlers.models.Device
import com.example.myapplication.databaseHandlers.repositories.ReservationDeviceRepository
import com.example.myapplication.databaseHandlers.repositories.ReservationRepository
import com.example.myapplication.databinding.AddedDeviceLayoutBinding
import com.example.myapplication.mainFragments.MyDevicesFragmentDirections
import java.text.SimpleDateFormat
import java.util.*

class AddedDeviceViewHolder(private val binding: AddedDeviceLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @RequiresApi(Build.VERSION_CODES.O)
    val reservationDeviceRepository = ReservationDeviceRepository(MainApplication.appContext)
    @RequiresApi(Build.VERSION_CODES.O)

    var reservationRepository =ReservationRepository(MainApplication.appContext)
    var cal = Calendar.getInstance()
    val myFormat = "yyyy/dd/MM" // mention the format you need
    val sdf = SimpleDateFormat(myFormat)

    val currentDate = cal.get(Calendar.DATE)





    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(device: Device) {
        onClick(device)
        setText(device)

        /*val reservationDateEndString=reservationRepository.getReservationEnd(device.deviceID).substring(0,9)
        val reservationDateStartString=reservationRepository.getReservationStart(device.deviceID).substring(0,9)

        val endDate: Date = sdf.parse(reservationDateEndString) as Date
        val startDate: Date = sdf.parse(reservationDateStartString) as Date


        val formatter = SimpleDateFormat("yyyy-dd-MM")
        val date = Date()



        val reservationTimeStartString=reservationRepository.getReservationStart(device.deviceID).substring(10)
        val reservationTimeEndString=reservationRepository.getReservationStart(device.deviceID).substring(10)
*/

        checkReservation(device)



       // compareDates(startDate, date)

    }

    private fun compareDates(startDate: Date, date: Date):Boolean {
        val cmp = startDate.compareTo(date)
        when {
            cmp > 0 -> {
                return false //data rozpoczecia jest po dzisiaj
            }
            cmp < 0 -> {
                return true//data rozpoczecia jest przed dzisiaj
            }
            else -> {
               return false
            }
        }
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

            if(!reservationDeviceRepository.checkReservation(device.deviceID)){
                val action = MyDevicesFragmentDirections.actionMyDevicesFragmentToSelectedDeviceFragment(device.deviceID)
                Navigation.findNavController(binding.root).navigate(action)
            }
            else{
                Toast.makeText(MainApplication.appContext,"device is already reserved",Toast.LENGTH_SHORT).show()
            }



        }
    }

    private fun setText(device: Device) {
        binding.deviceAddress.text = device.MACaddress
        binding.deviceName.text = device.description
        binding.deviceType.text=device.type
    }

}