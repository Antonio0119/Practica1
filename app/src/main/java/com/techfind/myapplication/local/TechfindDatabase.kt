package com.techfind.myapplication.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class, Add_service::class, Request_service::class], version = 1, exportSchema = false)
abstract class TechfindDatabase: RoomDatabase() {

    abstract fun UserDAO(): UserDAO
    abstract fun Add_serviceDAO(): Add_serviceDAO
    abstract fun Request_serviceDAO(): Request_serviceDAO

}