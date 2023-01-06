package com.example.myapplication.mainFragments.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.MainApplication
import com.example.myapplication.data.Room

class RoomsViewModel : ViewModel() {

    private val _roomsLiveData = MutableLiveData<List<Room>>(emptyList())
    val roomsLiveData: LiveData<List<Room>> = _roomsLiveData

    fun addRoom(){
        val room=MainApplication.rooms.lastOrNull()
        if(room==null){
            MainApplication.rooms.add(Room(1))
        }
        else{
            MainApplication.rooms.add(Room(room.id+1))
        }
        updateRooms()
    }

    private fun updateRooms() {
        _roomsLiveData.value=MainApplication.rooms.toList()
    }
}