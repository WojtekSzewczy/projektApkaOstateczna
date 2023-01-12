package com.example.myapplication.databaseHandlers.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.databaseHandlers.models.UserRoom

@Dao
interface UserRoomDao {
    @Insert
    fun addUserRoom( userRoom : UserRoom)

    @Query("SELECT UserID FROM UserRoom where RoomID = :roomID")
    fun getUsers(roomID : Int): List<Int>

    @Query("SELECT EXISTS(SELECT * FROM UserRoom WHERE UserID = :userID AND RoomID = :roomID)")
    fun checkIfUserRoomExists(userID : Int, roomID : Int) : Boolean
}