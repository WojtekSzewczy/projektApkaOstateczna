package com.example.myapplication.databaseHandlers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.myapplication.databaseModels.*
import java.io.File
import java.io.FileOutputStream
import java.util.Arrays
import java.security.MessageDigest
import java.util.Base64

@RequiresApi(Build.VERSION_CODES.O)
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
        Log.v("addUser","need help")
    }



    fun getUserId(login: String, password: String): Int {
        val db = readableDatabase
        // Pobierz instancję klasy MessageDigest z algorytmem SHA-256
        val md = MessageDigest.getInstance("SHA-256")

        // Przekonwertuj hasło na tablicę bajtów
        val passwordBytes = password.toByteArray(Charsets.UTF_8)

        // Oblicz skrót hasła
        val hash = md.digest(passwordBytes)

        // Konwertuj skrót na ciąg znaków w formacie Base64
        val hashPassword = Base64.getEncoder().encodeToString(hash)

        val cursor = db.query(
            UserTable.TABLE_NAME,
            null,
            "${UserTable.COLUMN_LOGIN} = ? AND ${UserTable.COLUMN_PASSWORD} = ?",
            arrayOf(login, hashPassword),
            null,
            null,
            null
        )
        if (cursor.moveToFirst()) {
            val id = cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COLUMN_USERID)).toInt()

            Log.v("DatabaseHelper", id.toString())
            cursor.close()
            db.close()
            return id
        }
        cursor.close()
        db.close()
        return -1
    }

    fun getDeviceId(device: Device): Int {
        val db = readableDatabase
        val macAddress= device.MACaddress


        val cursor = db.rawQuery("SELECT * FROM ${DeviceTable.TABLE_NAME} WHERE ${DeviceTable.COLUMN_MACADDRESS} ='$macAddress'" , null)
        cursor.moveToFirst()
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(DeviceTable.COLUMN_DEVICEID))
        cursor.close()
        db.close()
        return id
    }



    fun getUser(login: String, password: String): User? {
        val db = readableDatabase

        // Pobierz instancję klasy MessageDigest z algorytmem SHA-256
        val md = MessageDigest.getInstance("SHA-256")

        // Przekonwertuj hasło na tablicę bajtów
        val passwordBytes = password.toByteArray(Charsets.UTF_8)

        // Oblicz skrót hasła
        val hash = md.digest(passwordBytes)

        // Konwertuj skrót na ciąg znaków w formacie Base64
        val hashPassword = Base64.getEncoder().encodeToString(hash)

        val cursor = db.query(
            UserTable.TABLE_NAME,
            null,
            "${UserTable.COLUMN_LOGIN} = ? AND ${UserTable.COLUMN_PASSWORD} = ?",
            arrayOf(login, hashPassword),
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
    fun addDevice(device: Device) {
        val db = writableDatabase
        //device.id=getDeviceId(device)

        val values = device.toContentValues()

        db.insert(DeviceTable.TABLE_NAME, null, values)
        db.close()
    }
    fun getAllDevices(): MutableList<Device> {

        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${DeviceTable.TABLE_NAME}", null)
        val devices = mutableListOf<Device>()
        while (cursor.moveToNext()) {
            val device = Device(
                cursor.getString(cursor.getColumnIndexOrThrow(DeviceTable.COLUMN_OWNERID)).toInt(),
                cursor.getString(cursor.getColumnIndexOrThrow(DeviceTable.COLUMN_PASSWORD)),
                cursor.getString(cursor.getColumnIndexOrThrow(DeviceTable.COLUMN_TYPE)),
                cursor.getString(cursor.getColumnIndexOrThrow(DeviceTable.COLUMN_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndexOrThrow(DeviceTable.COLUMN_WIFI)).toInt(),
                cursor.getString(cursor.getColumnIndexOrThrow(DeviceTable.COLUMN_CONNECTION)),
                cursor.getString(cursor.getColumnIndexOrThrow(DeviceTable.COLUMN_GOOGLEASSISTANT)).toInt(),
                cursor.getString(cursor.getColumnIndexOrThrow(DeviceTable.COLUMN_LOCATION)),
                cursor.getString(cursor.getColumnIndexOrThrow(DeviceTable.COLUMN_MACADDRESS))
                )
            devices.add(device)
        }
        cursor.close()
        db.close()
        return devices
    }

    fun getAllRooms(): MutableList<Room> {

    val db = readableDatabase
    val cursor = db.rawQuery("SELECT * FROM ${RoomTable.TABLE_NAME}", null)
    val rooms = mutableListOf<Room>()
    while (cursor.moveToNext()) {
        val room = Room(
            cursor.getString(cursor.getColumnIndexOrThrow(RoomTable.COLUMN_OWNERID)).toInt(),
            cursor.getString(cursor.getColumnIndexOrThrow(RoomTable.COLUMN_NAME)),
            cursor.getString(cursor.getColumnIndexOrThrow(RoomTable.COLUMN_PASSWORD)),
            cursor.getString(cursor.getColumnIndexOrThrow(RoomTable.COLUMN_MAXPARTICIPANTS)).toInt(),
            cursor.getString(cursor.getColumnIndexOrThrow(RoomTable.COLUMN_CREATIONDATETIME)),
            cursor.getString(cursor.getColumnIndexOrThrow(RoomTable.COLUMN_CLOSEDATETIME)),

            )
        room.roomID= cursor.getInt(cursor.getColumnIndexOrThrow(RoomTable.COLUMN_ROOMID))

        rooms.add(room)
    }
    cursor.close()
    db.close()
    return rooms
}
    fun addRoom(room: Room) {
        val db = writableDatabase

        val values = room.toContentValues()
        db.insert(RoomTable.TABLE_NAME, null, values)
        db.close()
    }
    fun getDevicesinRoom():List<Room> {
        //TODO
        return emptyList()
    }
    //fun getRommByID(id:Int):Room{
    //}
   // fun addRommHistory():Roomhistory{

   // }



}