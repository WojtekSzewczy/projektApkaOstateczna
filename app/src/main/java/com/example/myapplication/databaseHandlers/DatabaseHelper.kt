package com.example.myapplication.databaseHandlers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.myapplication.databaseModels.User
import com.example.myapplication.databaseModels.UserTable
import java.io.File
import java.io.FileOutputStream
import java.util.Arrays


class DatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{
    companion object {
        private const val DATABASE_NAME = "database"
        private const val DATABASE_VERSION = 1
    }

    private fun copyDatabaseFromAssets() {
        val inputStream = context.assets.open(DATABASE_NAME)
        val outputFile = context.getDatabasePath(DATABASE_NAME)
        val outputStream = FileOutputStream(outputFile)

        val buffer = ByteArray(1024)
        var length: Int
        while (inputStream.read(buffer).also { length = it } > 0) {
            outputStream.write(buffer, 0, length)
        }

        outputStream.flush()
        inputStream.close()
        outputStream.close()
    }


    override fun onCreate(db: SQLiteDatabase?) {
        copyDatabaseFromAssets()

        val assetManager = context.assets
        val createTableStatement = assetManager.open("create_tables.sql").reader().use { it.readText() }
        val commands = createTableStatement.split(";")
        for (command in commands) {
            try {
                db?.execSQL(command)
            } catch (e: Exception) {
                Log.e("DatabaseHelper", "Error creating table", e)
            }
        }
        Log.v("DatabaseHelper", "Database created")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //TODO upgrade database
    }

//   TODO split this into multiple files for each table

    fun addUser(user: User) {
        val db = writableDatabase

        val values = user.toContentValues()
        db.insert(UserTable.TABLE_NAME, null, values)
        db.close()
    }



    fun getUserId(login: String, password: String): Int {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${UserTable.TABLE_NAME} WHERE ${UserTable.COLUMN_LOGIN} = '$login' AND ${UserTable.COLUMN_PASSWORD} = '$password'", null)
        cursor.moveToFirst()
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(UserTable.COLUMN_USERID))
        cursor.close()
        db.close()
        return id
    }

    fun getUser(login: String, password: String): User? {
        val db = readableDatabase

        val cursor = db.query(
            UserTable.TABLE_NAME,
            null,
            "${UserTable.COLUMN_LOGIN} = ? AND ${UserTable.COLUMN_PASSWORD} = ?",
            arrayOf(login, password),
            null,
            null,
            null
        )
        if (cursor.moveToFirst()) {
            val user = User(
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_SURNAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_POSITION)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_EMAIL)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_LOGIN)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_PASSWORD))
            )
            Log.v("DatabaseHelper", "User: $user")
            cursor.close()
            db.close()
            return user
        }
        cursor.close()
        db.close()
        return null
    }

    fun getAllUsers(): List<User> {

        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${UserTable.TABLE_NAME}", null)
        val users = mutableListOf<User>()
        while (cursor.moveToNext()) {
            val user = User(
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_SURNAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_POSITION)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_EMAIL)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_LOGIN)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_PASSWORD))
            )
            users.add(user)
        }
        cursor.close()
        db.close()
        return users
    }


}