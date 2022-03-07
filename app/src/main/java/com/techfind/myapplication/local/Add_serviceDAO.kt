package com.techfind.myapplication.local

import androidx.room.*

@Dao
interface Add_serviceDAO {
    @Insert
    suspend fun Service(service: Add_service)

    @Query("SELECT * FROM table_add_service")
    suspend fun loadServices(): MutableList<Add_service>

    @Query("SELECT * FROM table_add_service WHERE category LIKE :category")
    suspend fun searchService(category: String): Add_service

    @Delete
    suspend fun deleteService(service: Add_service)
}