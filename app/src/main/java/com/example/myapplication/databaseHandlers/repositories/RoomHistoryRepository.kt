package com.example.myapplication.databaseHandlers.repositories

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapplication.databaseHandlers.databases.MyAppDatabase
import com.example.myapplication.databaseHandlers.models.RoomHistory


@RequiresApi(Build.VERSION_CODES.O)
class RoomHistoryRepository(context: Context) {
    private val roomHistoryDao = MyAppDatabase.getInstance(context).roomHistoryDao()

    fun getDevicesId(roomId : Int): List<Int>{
        return roomHistoryDao.getDevicesId(roomId)
    }

    fun addNew(roomHistory: RoomHistory) {
        roomHistoryDao.addNew(roomHistory)
    }
}