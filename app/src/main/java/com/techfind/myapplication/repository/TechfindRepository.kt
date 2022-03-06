package com.techfind.myapplication.repository

import com.techfind.myapplication.Techfind
import com.techfind.myapplication.local.User
import com.techfind.myapplication.local.UserDAO
import java.sql.Types.NULL

class TechfindRepository {

    fun newUser(
        name: String,
        email: String,
        password: String,
        document: Int,
        cel_number: Int

    ) {
        val user = User(
            user_id = NULL,
            name = name,
            email = email,
            password = password,
            document = document,
            cel_number = cel_number,

        )

        val userDAO: UserDAO = Techfind.database.UserDAO()
        userDAO.newUser(user)
    }

}