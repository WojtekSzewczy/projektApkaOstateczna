package com.example.myapplication.data


data class Room ( var id : Int){
    lateinit var name :String
    var deviceList= mutableListOf<AddedDevice>()
}