package com.example.myapplication.data

import android.bluetooth.le.ScanResult
import android.util.Log

data class AddedDevice( val result: ScanResult){
    private lateinit var password :String
    var name = result.device.name
    val address = result.device.address
    lateinit var type:String
    private lateinit var room: Room

    fun addToRoom(room:Room){
        this.room=room
    }

    fun setPassword(password: String){
        Log.v("AddedDevice","haslo ")
        this.password=password
    }
}