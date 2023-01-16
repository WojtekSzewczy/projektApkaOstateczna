package com.example.myapplication.databaseHandlers.repositories

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapplication.databaseHandlers.databases.MyAppDatabase
import com.example.myapplication.databaseHandlers.models.Device
@RequiresApi(Build.VERSION_CODES.O)
class DeviceRepository(context: Context) {

    private val deviceDao = MyAppDatabase.getInstance(context).deviceDao()

    fun getAllDevices() : List<Device> = deviceDao.getAllDevices()

    fun addDevice(device: Device?)
    {
        if(device?.MACaddress==null){
            device?.MACaddress="null"
        }
        deviceDao.addDevice(device?.ownerID,device?.password,device?.type,device?.description,device?.wifi,device?.connection,device?.googleassistant,device?.location,device?.MACaddress)
    }
    fun getDeviceById(deviceId :Int): Device{
        return deviceDao.getDeviceById(deviceId)
    }
    fun getDevicePassword(deviceId: Int):String{
        return deviceDao.getDevicePassword(deviceId)
    }
}