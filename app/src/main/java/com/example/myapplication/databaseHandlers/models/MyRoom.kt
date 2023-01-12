package com.example.myapplication.databaseHandlers.models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = "Room",
    indices = [Index(value = ["roomID"], unique = true)],
    foreignKeys = [ForeignKey(entity = User::class,
        parentColumns = arrayOf("UserID"),
        childColumns = arrayOf("ownerID"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)])
data class MyRoom(
    var ownerID: Int,
    var name: String,
    var password: String,
    var maxParticipants: Int,
    var creationDateTime: String,
    var closeDateTime: String
)
{
    @PrimaryKey(autoGenerate = true)
    var roomID: Int = 0
}

class RoomTable {
    companion object {
        const val TABLE_NAME = "Room"
        const val COLUMN_ROOMID = "RoomID"
        const val COLUMN_OWNERID = "OwnerID"
        const val COLUMN_NAME = "Name"
        const val COLUMN_PASSWORD = "Password"
        const val COLUMN_MAXPARTICIPANTS = "MaxParticipants"
        const val COLUMN_CREATIONDATETIME = "CreationDateTime"
        const val COLUMN_CLOSEDATETIME = "CloseDateTime"
    }
}