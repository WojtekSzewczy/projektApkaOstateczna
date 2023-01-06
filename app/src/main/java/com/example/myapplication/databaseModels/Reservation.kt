package com.example.myapplication.databaseModels


data class Reservation(
    val reservationid: Int,
    val bookerid: Int,
    val reservationstart: String,
    val reservationend: String
)

class ReservationTable{
    companion object {
        const val TABLE_NAME = "Reservation"
        const val COLUMN_RESERVATIONID = "ReservationID"
        const val COLUMN_BOOKERID = "BookerID"
        const val COLUMN_RESERVATIONSTART = "ReservationStart"
        const val COLUMN_RESERVATIONEND = "ReservationEnd"
    }
}