package com.example.myapplication.databaseHandlers.repositories

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapplication.databaseHandlers.databases.MyAppDatabase

@RequiresApi(Build.VERSION_CODES.O)
class ReservationDeviceRepository(context: Context) {
    private val reservationDeviceDao = MyAppDatabase.getInstance(context).reservationDeviceDao()

    fun addReservationDeice(deviceId : Int, reservationId : Int) {
        reservationDeviceDao.addReservationDeice(reservationId,deviceId)
    }
    fun checkReservation(deviceId : Int): Boolean{
        return reservationDeviceDao.checkReservation(deviceId)
    }

}