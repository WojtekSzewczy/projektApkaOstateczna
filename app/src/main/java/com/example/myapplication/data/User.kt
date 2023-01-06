package com.example.myapplication.data

//TODO change this user to user in databaseModels
class User(val id: Int ) {
    private lateinit var login: String
    private lateinit var password: String
    val userDevices = mutableListOf<AddedDevice>()

    fun addDevice(device : AddedDevice){
        userDevices.add(device)
    }

}