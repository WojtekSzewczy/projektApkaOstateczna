package com.example.myapplication.databaseModels

data class ReservationDevice(
    val reservationid: Int,
    val deviceid: Int
)


class ReservationDeviceTable {
    companion object {
        const val TABLE_NAME = "Reservation_Device"
        const val COLUMN_RESERVATIONID = "ReservationID"
        const val COLUMN_DEVICEID = "DeviceID"
    }
}