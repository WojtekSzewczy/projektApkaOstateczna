package com.example.myapplication.mainFragments.viewModels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.MainApplication
import com.example.myapplication.databaseHandlers.models.MyRoom
import com.example.myapplication.databaseHandlers.repositories.MyRoomRepository

class RoomsViewModel : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    val myRoomRepository = MyRoomRepository(MainApplication.appContext)
    @RequiresApi(Build.VERSION_CODES.O)
    private val roomsList =myRoomRepository.getAllRooms()
    @RequiresApi(Build.VERSION_CODES.O)
    private val _roomsLiveData = MutableLiveData<List<MyRoom>>(roomsList)
    @RequiresApi(Build.VERSION_CODES.O)
    val roomsLiveData: LiveData<List<MyRoom>> = _roomsLiveData

    @RequiresApi(Build.VERSION_CODES.O)
    fun addRoom(){
        val room=roomsList.lastOrNull()
        if(room==null){
           // db.addRoom()
        }
        else{
           // MainApplication.rooms.add(Room(room.id+1))
        }
        updateRooms()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateRooms() {
      //  _roomsLiveData.value=room
    }
}