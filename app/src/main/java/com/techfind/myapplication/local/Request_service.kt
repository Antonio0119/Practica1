package com.techfind.myapplication.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Types.NULL
import java.io.Serializable

@Entity(tableName= "table_request_service")
data class Request_service(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "request_id") var request_id: Int = NULL,
    @ColumnInfo(name = "year") var year: Int = 0,
    @ColumnInfo(name = "month") var month: Int = 0,
    @ColumnInfo(name = "day") var day: Int = 0,
    @ColumnInfo(name = "hours") var hours: Int = 0,
    @ColumnInfo(name = "address") var address: String = "",
    @ColumnInfo(name = "service_description") var service_description: String = "",

) : Serializable
