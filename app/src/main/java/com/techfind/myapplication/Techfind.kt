package com.techfind.myapplication

import android.app.Application
import androidx.room.Room
import com.techfind.myapplication.local.TechfindDatabase

class Techfind : Application() {

    companion object{
        lateinit var database: TechfindDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            TechfindDatabase::class.java,
            "techfind_db"
        ).build()
    }

}