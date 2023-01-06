package com.example.myapplication.databaseModels

data class Device(val deviceid: Int, val ownerid: Int, val password: String, val type:String, val description:String, val wifi: Int, val connection:String, val googleassistant : Int, val location: String)


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