package com.example.myapplication.databaseHandlers.models


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = "UserRoom",
    foreignKeys = [ForeignKey(entity = User::class,
        parentColumns = arrayOf("UserID"),
        childColumns = arrayOf("userID"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE),

        ForeignKey(entity = MyRoom::class,
            parentColumns = arrayOf("roomID"),
            childColumns = arrayOf("roomID"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE)])
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