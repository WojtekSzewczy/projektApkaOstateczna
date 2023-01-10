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

    @Query("INSERT INTO user (login, password, name, surname, email, position) VALUES (:login, :password, :name, :surname, :email, :position)")
    fun addUser(login : String, password : String, name : String, surname : String, email : String, position : String)


}