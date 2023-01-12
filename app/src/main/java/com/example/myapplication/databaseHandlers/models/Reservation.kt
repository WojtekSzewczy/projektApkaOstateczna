package com.example.myapplication.databaseHandlers.models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = "reservation",
    indices = [Index(value = ["reservationID"], unique = true)],
    foreignKeys = [ForeignKey(entity = User::class,
        parentColumns = arrayOf("UserID"),
        childColumns = arrayOf("bookerID"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)])
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