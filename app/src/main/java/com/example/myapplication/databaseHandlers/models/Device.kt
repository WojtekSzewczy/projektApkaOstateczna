package com.example.myapplication.databaseHandlers.models

import android.content.ContentValues
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.*

@Entity(tableName = "device")
data class Device @RequiresApi(Build.VERSION_CODES.O) constructor(
    @ColumnInfo(name = "OwnerID")
    var ownerID: Int,
    @ColumnInfo(name = "Password")
    var password: String,
    @ColumnInfo(name = "Type")
    var type:String,
    @ColumnInfo(name = "Description")
    var description:String,
    @ColumnInfo(name = "Wifi")
    var wifi: Int,
    @ColumnInfo(name = "Connection")
    var connection:String,
    @ColumnInfo(name = "GoogleAssistant")
    var googleassistant : Int,
    @ColumnInfo(name = "Location")
    var location: String,
    @ColumnInfo(name = "MACaddress")
    var MACaddress : String){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "DeviceID")
    var deviceID: Int = 0

//    @ForeignKey(entity = User::class, parentColumns = ["UserID"], childColumns = ["OwnerID"], onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
    //@ColumnInfo(name = "isReserved")
    @Ignore var isReserved=false

}


class DeviceTable {
    companion object {
        const val TABLE_NAME = "Device"
        const val COLUMN_DEVICEID = "DeviceID"
        const val COLUMN_OWNERID = "OwnerID"
        const val COLUMN_PASSWORD = "Password"
        const val COLUMN_TYPE = "Type"
        const val COLUMN_DESCRIPTION = "Description"
        const val COLUMN_WIFI = "Wi-fi"
        const val COLUMN_CONNECTION = "Connection"
        const val COLUMN_GOOGLEASSISTANT = "GoogleAssistant"
        const val COLUMN_LOCATION = "Location"
        const val COLUMN_MACADDRESS = "MACAddress"
    }
}