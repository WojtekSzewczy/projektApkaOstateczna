package com.example.myapplication.databaseHandlers.repositories

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapplication.databaseHandlers.databases.MyAppDatabase


@RequiresApi(Build.VERSION_CODES.O)
class RoomHistoryRepository(context: Context) {
    private val roomHistoryDao = MyAppDatabase.getInstance(context).roomHistoryDao()
}