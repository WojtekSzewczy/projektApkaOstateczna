package com.example.myapplication.databaseHandlers.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.databaseHandlers.models.Device

@Dao
interface DeviceDao {

    @Query("SELECT * FROM device")
    fun getAllDevices() : List<Device>

    @Query("INSERT INTO device (OwnerID,Password,Type,Description,Wifi,Connection,GoogleAssistant,Location,MACaddress) VALUES (:ownerID,:password,:type,:description,:wifi,:connection,:googleassistant,:location,:MACaddress)")
    fun addDevice(ownerID: Int,password: String,type: String,description: String,wifi: Int,connection: String,googleassistant: Int,location: String,MACaddress: String)
}