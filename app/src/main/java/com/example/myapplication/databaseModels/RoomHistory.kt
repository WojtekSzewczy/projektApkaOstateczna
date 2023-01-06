package com.example.myapplication.databaseModels

data class Roomhistory(
    val roomid: Int,
    val deviceid: Int
)

class RoomHistoryTable {
    companion object {
        const val TABLE_NAME = "RoomHistory"
        const val COLUMN_ROOMID = "RoomID"
        const val COLUMN_DEVICEID = "DeviceID"
    }
}