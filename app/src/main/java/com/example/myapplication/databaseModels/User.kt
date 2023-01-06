package com.example.myapplication.databaseModels

import android.content.ContentValues
import com.example.myapplication.data.AddedDevice

data class User(val Name: String, val Surname : String, val Position : String, val Email : String, val Login : String, val Password : String)
{
    val userDevices = mutableListOf<AddedDevice>()

    fun addDevice(device : AddedDevice){
        userDevices.add(device)
    }

    fun toContentValues() = ContentValues().apply {
        put(UserTable.COLUMN_NAME, Name)
        put(UserTable.COLUMN_SURNAME, Surname)
        put(UserTable.COLUMN_POSITION, Position)
        put(UserTable.COLUMN_EMAIL, Email)
        put(UserTable.COLUMN_LOGIN, Login)
        put(UserTable.COLUMN_PASSWORD, Password)
    }

    override fun toString(): String {
        return "User(Name='$Name', Surname='$Surname', Position='$Position', Email='$Email', Login='$Login', Password='$Password', userDevices=$userDevices)"
    }
}

class UserTable {
    companion object {
        const val TABLE_NAME = "User"
        const val COLUMN_USERID = "UserID"
        const val COLUMN_NAME = "Name"
        const val COLUMN_SURNAME = "Surname"
        const val COLUMN_POSITION = "Position"
        const val COLUMN_EMAIL = "Email"
        const val COLUMN_LOGIN = "Login"
        const val COLUMN_PASSWORD = "Password"
    }
}