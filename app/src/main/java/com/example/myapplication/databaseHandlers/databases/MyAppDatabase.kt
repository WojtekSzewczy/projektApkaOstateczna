package com.example.myapplication.databaseHandlers.databases

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.databaseHandlers.dao.*
import com.example.myapplication.databaseHandlers.models.*

@RequiresApi(Build.VERSION_CODES.O)
@Database(entities = [Device::class, MyRoom::class, Reservation::class, ReservationDevice::class, RoomHistory::class, User::class, UserDevice::class], version = 1, exportSchema = false)
abstract class MyAppDatabase : RoomDatabase() {
    abstract fun deviceDao(): DeviceDao
    abstract fun myRoomDao(): MyRoomDao
    abstract fun reservationDao(): ReservationDao
    abstract fun reservationDeviceDao(): ReservationDeviceDao
    abstract fun roomHistoryDao(): RoomHistoryDao
    abstract fun userDao(): UserDao
    abstract fun userDeviceDao(): UserDeviceDao


    companion object {
        @Volatile
        private var instance: MyAppDatabase? = null

        fun getInstance(context: Context): MyAppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    MyAppDatabase::class.java,
                    "device_database"
                ).allowMainThreadQueries().build()
            }
        }
    }
}