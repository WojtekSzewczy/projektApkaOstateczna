package com.example.myapplication.databaseHandlers.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ReservationDeviceDao {

    @Query("INSERT INTO Reservation_Device (ReservationID,DeviceID) VALUES ( :reservationId,:deviceId )")
    fun addReservationDeice(deviceId : Int, reservationId : Int)

    @Query("SELECT EXISTS(SELECT * FROM Reservation_Device WHERE DeviceID = :deviceId)")
    fun checkReservation(deviceId : Int): Boolean

}