package com.example.myapplication

import android.bluetooth.*
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.os.postDelayed
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.ScannedDevice

object Scanner {

    val bluetoothAdapter by lazy {
        val bluetoothManager = getSystemService(
            MainApplication.appContext,
            BluetoothManager::class.java
        ) as BluetoothManager

        bluetoothManager.adapter
    }
    public var uuids="kk"

    private val handler = Handler(Looper.getMainLooper())
    private val DELAY_PERIOD: Long = 500

    private var bluetoothGatt: BluetoothGatt? = null

    private val bluetoothGattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            when (newState) {
                BluetoothAdapter.STATE_CONNECTED -> {
                    bluetoothGatt = gatt
                    gatt.discoverServices()
                }
                BluetoothAdapter.STATE_DISCONNECTED -> {
                    gatt.close()
                    bluetoothGatt=null
                }
            }

        }

        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
            super.onServicesDiscovered(gatt, status)
            gatt.services.forEach {
                Log.v("Service",it.uuid.toString())
            }
            uuids=getUUIDs()
            Log.v("UUIDs", uuids)


        }
    }
    fun getUUIDs(): String = buildString {
        bluetoothGatt?.services?.forEach {
            append("SERVICE  UUID: ")
            append(it.uuid)
            appendLine()

        }
    }


    val scanner = bluetoothAdapter.bluetoothLeScanner

    private val scanSettings =
        ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY).build()

    private val currentScannedDevices = mutableMapOf<String, ScannedDevice>()
    private val _scannedDevices = MutableLiveData<List<ScannedDevice>>(emptyList())
    val scannedDevices: LiveData<List<ScannedDevice>> = _scannedDevices

    private val _isScanning = MutableLiveData(false)
    val isScanning: LiveData<Boolean> = _isScanning

    //private var scanning = false


    private val runnableStoppingScanning = {
        _isScanning.value = false
        scanner.stopScan(leScanCallback)
    }

    fun startBleScan() {
        Log.v("startScan","wziuuum")
       /* if (isScanning.value!!) {
            return
        }*/
        //clearScanList()
        handler.postDelayed(runnableStoppingScanning, SCAN_PERIOD_IN_MS)
        _isScanning.value = true

        scanner.startScan(null, scanSettings, leScanCallback)
    }

    const val SCAN_PERIOD_IN_MS: Long = 10000

    fun clearScanList() {
        currentScannedDevices.clear()
        updateScannedDevices()
    }

    private fun updateScannedDevices() {
        currentScannedDevices.values.forEach{
            Log.v("scanner", it.address)
        }
        _scannedDevices.value = currentScannedDevices.values.toList()
    }

    fun stopBleScan() {
        handler.removeCallbacks(runnableStoppingScanning)
        scanner.stopScan(leScanCallback)
        _isScanning.value = false
    }

    fun postScanningValue(value: Boolean) {
        _isScanning.postValue(value)
    }

    private val leScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            Log.v("result","lel")
            result?.let {
                val device = ScannedDevice(result)
                if (!currentScannedDevices.contains(device.address)) {
                    addDevice(device)
                }
            }
        }
    }

    private fun addDevice(device: ScannedDevice) {
        currentScannedDevices[device.address] = device
        updateScannedDevices()
    }
    fun connect(result: ScanResult) {
        bluetoothGatt = result.device.connectGatt(
            MainApplication.appContext, false, bluetoothGattCallback
        )
    }


}