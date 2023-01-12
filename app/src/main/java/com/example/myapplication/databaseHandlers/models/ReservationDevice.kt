package com.example.myapplication.databaseHandlers.models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = "Reservation_Device",
    foreignKeys = [ForeignKey(entity = Reservation::class,
        parentColumns = arrayOf("reservationID"),
        childColumns = arrayOf("reservationID"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE),
    ForeignKey(entity = Device::class,
        parentColumns = arrayOf("DeviceID"),
        childColumns = arrayOf("deviceID"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)])
data class ReservationDevice(
    var reservationID: Int,
    var deviceID: Int
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