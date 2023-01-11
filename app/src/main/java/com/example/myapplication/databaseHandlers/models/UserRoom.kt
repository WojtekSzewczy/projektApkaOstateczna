package com.example.myapplication.databaseHandlers.models


import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "UserRoom")
data class UserRoom(
    var userID: Int,
    var roomID: Int
)


{   @PrimaryKey(autoGenerate = true)
    var userRoomID: Int = 0
}


class UserRoomTable {
    companion object {
        const val TABLE_NAME = "UserRoom"
        const val COLUMN_USERROOMID = "UserRoomID"
        const val COLUMN_USERID = "UserID"
        const val COLUMN_ROOMID = "RoomID"
    }
}