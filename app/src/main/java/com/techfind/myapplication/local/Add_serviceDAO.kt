package com.techfind.myapplication.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Add_serviceDAO {
    @Insert
    suspend fun Service(service: Add_service)

    @Query("SELECT * FROM table_add_service")
    suspend fun loadServices(): MutableList<Add_service>

    /*
    @Query("SELECT * FROM table_service WHERE Category LIKE :category")
    suspend fun searchService(category: String): Add_service*/
}