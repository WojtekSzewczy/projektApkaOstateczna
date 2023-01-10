package com.example.myapplication.databaseHandlers.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.databaseHandlers.models.MyRoom

@Dao
interface MyRoomDao {

    @Query("SELECT * FROM room")
    fun getAllRooms() : List<MyRoom>

    @Query("INSERT INTO room (OwnerID,Name,Password,MaxParticipants,CreationDateTime,CloseDateTime) VALUES (:ownerid,:name,:password,:maxParticipants,:creationDateTime,:closeDateTime)")
    fun addRoom(ownerid: Int,name: String,password: String,maxParticipants: Int,creationDateTime: String,closeDateTime: String)
}