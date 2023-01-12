package com.example.myapplication.mainFragments.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplication.Scanner

class DevicesViewModel : ViewModel() {

    val devices = Scanner.scannedDevices
    val isScanning = Scanner.isScanning

    fun switchScanning() {
        val currentState = isScanning.value!!
        if (currentState) {
            Scanner.stopBleScan()
        } else {
            Scanner.startBleScan()
        }
        Scanner.postScanningValue(!currentState)
    }
}