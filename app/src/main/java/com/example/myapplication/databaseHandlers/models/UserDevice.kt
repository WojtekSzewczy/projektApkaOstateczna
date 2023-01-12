package com.example.myapplication.databaseHandlers.models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = "User_Device",
    foreignKeys = [ForeignKey(entity = User::class,
        parentColumns = arrayOf("UserID"),
        childColumns = arrayOf("userID"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE),

        ForeignKey(entity = Device::class,
            parentColumns = arrayOf("DeviceID"),
            childColumns = arrayOf("deviceID"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE),

        ForeignKey(entity = User::class,
            parentColumns = arrayOf("UserID"),
            childColumns = arrayOf("ownerID"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE)])
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