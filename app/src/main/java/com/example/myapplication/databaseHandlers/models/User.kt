package com.example.myapplication.databaseHandlers.models

import android.content.ContentValues
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.myapplication.data.AddedDevice
import java.security.MessageDigest
import java.util.Base64

@RequiresApi(Build.VERSION_CODES.O)

@Entity(tableName = "User",
    indices = [Index(value = ["UserID"], unique = true),
        Index(value = ["Email"], unique = true),
        Index(value = ["Login"], unique = true)])
data class User(
                var Name: String,
                var Surname : String,
                var Position : String,
                var Email : String,
                var Login : String,
                var Password : String)
{
    @PrimaryKey(autoGenerate = true)
    var UserID: Int = 0

    @Ignore
    val userDevices = mutableListOf<AddedDevice>()
    init{
        val md = MessageDigest.getInstance("SHA-256")

        val passwordBytes = Password.toByteArray(Charsets.UTF_8)

        val hash = md.digest(passwordBytes)

        Password = Base64.getEncoder().encodeToString(hash)
    }

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