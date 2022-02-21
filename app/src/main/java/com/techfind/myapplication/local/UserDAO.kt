package com.techfind.myapplication.local

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface UserDAO {

    @Insert
    fun newUser(user: User)
}