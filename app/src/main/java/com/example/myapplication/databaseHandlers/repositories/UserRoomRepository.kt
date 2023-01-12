package com.example.myapplication.databaseHandlers.repositories


import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapplication.databaseHandlers.databases.MyAppDatabase
import com.example.myapplication.databaseHandlers.models.UserRoom
@RequiresApi(Build.VERSION_CODES.O)
class UserRoomRepository(context: Context) {

    private val userRoomDao = MyAppDatabase.getInstance(context).userRoomDao()

  //  fun getAllUserRooms() : List<UserRoom> = userRoomDao.getAllUserRooms()
    fun addUserRoom(userRoom: UserRoom)
    {
        userRoomDao.addUserRoom(userRoom)
    }

    fun getUsersID(roomID : Int): List<Int>{
        return userRoomDao.getUsers(roomID)

    }

    fun checkIfUserRoomExists(userID : Int, roomID : Int) : Boolean {
       return userRoomDao.checkIfUserRoomExists(userID, roomID)
    }
}



