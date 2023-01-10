package com.example.myapplication.databaseHandlers.repositories

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapplication.databaseHandlers.databases.MyAppDatabase

@RequiresApi(Build.VERSION_CODES.O)
class UserDeviceRepository(context: Context) {
    private val userDeviceDao = MyAppDatabase.getInstance(context).userDeviceDao()
}