package com.example.myapplication.databaseHandlers.models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = "RoomHistory",
    foreignKeys = [ForeignKey(entity = MyRoom::class,
        parentColumns = arrayOf("roomID"),
        childColumns = arrayOf("roomID"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE),
    ForeignKey(entity = Device::class,
        parentColumns = arrayOf("DeviceID"),
        childColumns = arrayOf("deviceID"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)])
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