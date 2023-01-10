package com.example.myapplication.databaseHandlers.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User_Device")
data class UserDevice(
    var deviceID: Int,
    var userID: Int,
    var ownerID: Int
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}

class UserDeviceTable {
    companion object {
        const val TABLE_NAME = "User_Device"
        const val COLUMN_DEVICEID = "DeviceID"
        const val COLUMN_USERID = "UserID"
        const val COLUMN_OWNER = "Owner"
    }
}