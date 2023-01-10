package com.example.myapplication.databaseHandlers.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Reservation_Device")
data class ReservationDevice(
    var reservationID: Int,
    var deviceiD: Int
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}


class ReservationDeviceTable {
    companion object {
        const val TABLE_NAME = "Reservation_Device"
        const val COLUMN_RESERVATIONID = "ReservationID"
        const val COLUMN_DEVICEID = "DeviceID"
    }
}