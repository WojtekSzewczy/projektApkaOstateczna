package com.example.myapplication.databaseHandlers.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ReservationDao {

    @Query("INSERT INTO reservation (BookerID,ReservationStart,ReservationEnd) VALUES (:bookerId, :reservationStart, :reservationEnd)")
    fun addReservation(bookerId: Int, reservationStart :String, reservationEnd : String)

    @Query("SELECT ReservationID FROM Reservation WHERE BookerID=:bookerId and ReservationStart =:reservationStart and ReservationEnd=:reservationEnd")
    fun getReservationId(bookerId: Int, reservationStart :String, reservationEnd : String): Int


}