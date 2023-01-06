package com.example.myapplication.databaseModels

data class UserDevice(
    val deviceid: Int,
    val userid: Int,
    val owner: Int
)

class UserDeviceTable {
    companion object {
        const val TABLE_NAME = "User_Device"
        const val COLUMN_DEVICEID = "DeviceID"
        const val COLUMN_USERID = "UserID"
        const val COLUMN_OWNER = "Owner"
    }
}