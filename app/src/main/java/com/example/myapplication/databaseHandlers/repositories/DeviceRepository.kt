package com.example.myapplication.databaseHandlers.repositories

import android.content.Context
import com.example.myapplication.databaseHandlers.databases.MyAppDatabase
import com.example.myapplication.databaseHandlers.models.Device

class DeviceRepository(context: Context) {
    private val deviceDao = MyAppDatabase.getInstance(context).deviceDao()

    fun getAllDevices() : List<Device> = deviceDao.getAllDevices()

    fun addDevice(device: Device)
    {
        deviceDao.addDevice(device.ownerID,device.password,device.type,device.description,device.wifi,device.connection,device.googleassistant,device.location,device.MACaddress)
    }
}