package com.techfind.myapplication.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {

    @Insert
    fun newUser(user: User)

}