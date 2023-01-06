package com.example.myapplication

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.myapplication.data.AddedDevice
import com.example.myapplication.data.Room
import com.example.myapplication.databaseModels.User

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        //TODO admin moze usuwac/dodawac userów, jesli admin nie nada uprawnien to nie widzis urzadzen w sali, admin moze usuwac dowolny pokoj a user tylko swój
    }

    companion object {
        lateinit var appContext: Context
        lateinit var currentUser: User
        val rooms = mutableListOf<Room>()
        lateinit var userID: String
    }
}
