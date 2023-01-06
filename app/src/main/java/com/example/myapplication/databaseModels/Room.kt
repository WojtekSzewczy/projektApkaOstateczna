package com.example.myapplication.databaseModels

import android.content.ContentValues

data class Room(
    val ownerid: Int,
    val name: String,
    val password: String,
    val maxparticipants: Int,
    val creationdatetime: String,
    val closedatetime: String
)
{
    fun toContentValues() = ContentValues().apply {
        put(RoomTable.COLUMN_OWNERID, ownerid)
        put(RoomTable.COLUMN_NAME, name)
        put(RoomTable.COLUMN_PASSWORD, password)
        put(RoomTable.COLUMN_MAXPARTICIPANTS, maxparticipants)
        put(RoomTable.COLUMN_CREATIONDATETIME, creationdatetime)
        put(RoomTable.COLUMN_CLOSEDATETIME, closedatetime)
    }
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