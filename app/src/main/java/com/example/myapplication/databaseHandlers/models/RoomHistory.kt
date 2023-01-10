package com.example.myapplication.databaseHandlers.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RoomHistory")
data class RoomHistory(
    var roomID: Int,
    var deviceID: Int
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

class RoomHistoryTable {
    companion object {
        const val TABLE_NAME = "RoomHistory"
        const val COLUMN_ROOMID = "RoomID"
        const val COLUMN_DEVICEID = "DeviceID"
    }
}