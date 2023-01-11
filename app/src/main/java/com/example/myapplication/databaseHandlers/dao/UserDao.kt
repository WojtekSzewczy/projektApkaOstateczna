package com.example.myapplication.databaseHandlers.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.databaseHandlers.models.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE login = :login AND password = :password")
    fun getUser(login : String, password : String): User

    @Query("SELECT EXISTS(SELECT * FROM user WHERE email = :email)")
    fun checkIfUserExistsByEmail(email : String) : Boolean

    @Query("SELECT * FROM user")
    fun getAllUsers() : List<User>

    @Query("INSERT INTO user (login, password, name, surname, email, position) VALUES (:login, :password, :name, :surname, :email, :position)")
    fun addUser(login : String, password : String, name : String, surname : String, email : String, position : String)

    @Query("UPDATE user SET Password = :newPassword WHERE Email = :email")
    fun updatePassword(email: String, newPassword: String)


}