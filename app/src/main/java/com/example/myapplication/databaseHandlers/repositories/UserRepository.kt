package com.example.myapplication.databaseHandlers.repositories

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.myapplication.databaseHandlers.databases.MyAppDatabase
import com.example.myapplication.databaseHandlers.models.User
import java.security.MessageDigest
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class UserRepository(context: Context) {
    private val userDao = MyAppDatabase.getInstance(context).userDao()

    @RequiresApi(Build.VERSION_CODES.O)
    fun getUser(login : String, password : String): User
    {
        val md = MessageDigest.getInstance("SHA-256")

        val passwordBytes = password.toByteArray(Charsets.UTF_8)

        val hash = md.digest(passwordBytes)

        val hashPassword = Base64.getEncoder().encodeToString(hash)

        return userDao.getUser(login, hashPassword)
    }

    fun getUserByID(userId: Int):User{
        return userDao.getUserById(userId)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addUser(user : User)
    {
        userDao.addUser(user.Login, user.Password, user.Name, user.Surname, user.Email, user.Position)
    }

    fun getAllUsers() : List<User> = userDao.getAllUsers()

    fun checkIfUserExistsByEmail(email : String) : Boolean = userDao.checkIfUserExistsByEmail(email)

    fun updatePassword(email: String, newPassword: String)
    {
        userDao.updatePassword(email, newPassword)
    }
}