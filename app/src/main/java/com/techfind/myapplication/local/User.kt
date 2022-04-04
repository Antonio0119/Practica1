package com.techfind.myapplication.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Types.NULL
import java.io.Serializable

@Entity(tableName= "table_user")
data class User(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "user_id") var user_id: Int = NULL,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "email") var email: String = "",
    @ColumnInfo(name = "password") var password: String = "",
    @ColumnInfo(name = "document") var document: Long = 0,
    @ColumnInfo(name = "cel_number") var cel_number: Long = 0,

    ) : Serializable
