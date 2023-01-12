package com.example.myapplication.databaseHandlers.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.databaseHandlers.models.RoomHistory

@Dao
interface RoomHistoryDao {

    @Query("SELECT DeviceID FROM RoomHistory WHERE RoomID = :roomId")
    fun getDevicesId(roomId: Int) : List<Int>
    @Insert
    fun addNew(roomHistory: RoomHistory)

}