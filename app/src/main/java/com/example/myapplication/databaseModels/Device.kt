package com.example.myapplication.databaseModels

import android.content.ContentValues

data class Device(val deviceid: Int, val ownerid: Int, val password: String, val type:String, val description:String, val wifi: Int, val connection:String, val googleassistant : Int, val location: String) {

    fun toContentValues() = ContentValues().apply {
        put(DeviceTable.COLUMN_DEVICEID, deviceid)
        put(DeviceTable.COLUMN_OWNERID, ownerid)
        put(DeviceTable.COLUMN_PASSWORD, password)
        put(DeviceTable.COLUMN_TYPE, type)
        put(DeviceTable.COLUMN_DESCRIPTION, description)
        put(DeviceTable.COLUMN_WIFI, wifi)
        put(DeviceTable.COLUMN_CONNECTION, connection)
        put(DeviceTable.COLUMN_GOOGLEASSISTANT, googleassistant)
        put(DeviceTable.COLUMN_LOCATION, location)


    }
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
    }
}