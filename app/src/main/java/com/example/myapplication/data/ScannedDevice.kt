package com.example.myapplication.data

import android.bluetooth.le.ScanResult

data class ScannedDevice( val result: ScanResult) {
    var name = result.device.name
    val address = result.device.address

}