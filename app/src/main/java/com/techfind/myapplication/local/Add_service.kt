package com.techfind.myapplication.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Types.NULL
import java.io.Serializable

@Entity(tableName= "table_add_service")
data class Add_service(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "service_id") var service_id: Int = NULL,
    @ColumnInfo(name = "category") var category: String = "",
    @ColumnInfo(name = "long_description") var long_description: String = "",
    @ColumnInfo(name = "short_description") var short_description: String = "",
    @ColumnInfo(name = "service_price") var service_price: Int = 0,
    @ColumnInfo(name = "years_experience") var years_experience: Int = 0,

) : Serializable
