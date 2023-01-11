package com.example.myapplication.databaseHandlers.repositories

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapplication.databaseHandlers.databases.MyAppDatabase
import com.example.myapplication.databaseHandlers.models.MyRoom

@RequiresApi(Build.VERSION_CODES.O)
class MyRoomRepository(context: Context) {
    private val roomDao = MyAppDatabase.getInstance(context).myRoomDao()

    fun getAllRooms() : List<MyRoom> = roomDao.getAllRooms()

    fun addRoom(myRoom: MyRoom)
    {
        roomDao.addRoom(myRoom.ownerID,myRoom.name,myRoom.password,myRoom.maxParticipants,myRoom.creationDateTime,myRoom.closeDateTime)
    }

    fun getRoom(id: Int) : MyRoom
    {
        return roomDao.getRoom(id)
    }

    fun deleteRoom(myRoom: MyRoom)
    {
        roomDao.deleteRoom(myRoom)
    }
}