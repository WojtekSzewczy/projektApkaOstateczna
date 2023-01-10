package com.example.myapplication.databaseHandlers.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reservation")
data class Reservation(
    var bookerID: Int,
    var reservationStart:String,
    var reservationEnd:String)
{
    @PrimaryKey(autoGenerate = true)
    var reservationID: Int = 0
}

class ReservationTable{
    companion object {
        const val TABLE_NAME = "Reservation"
        const val COLUMN_RESERVATIONID = "ReservationID"
        const val COLUMN_BOOKERID = "BookerID"
        const val COLUMN_RESERVATIONSTART = "ReservationStart"
        const val COLUMN_RESERVATIONEND = "ReservationEnd"
    }
}