package com.example.myapplication

import android.app.Application
import android.content.Context
import com.example.myapplication.databaseHandlers.models.User

class MainApplication : Application() {

    override fun onCreate() {

        super.onCreate()
        appContext = this
        //TODO admin moze usuwac/dodawac userów , jesli admin nie nada uprawnien to nie widzis urzadzen w sali, admin moze usuwac dowolny pokoj a user tylko swój
        // nowy fragment dla admina do usuwania userow
    }
    fun dokumentacja(){
        // TODO
    }

    companion object {
        val adminID =1
        var isAdmin: Boolean = false
        lateinit var appContext: Context
        lateinit var currentUser: User
        var currentUserID= -1
    }
}
