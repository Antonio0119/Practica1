package com.techfind.myapplication.server

data class User(
    var uid: String? = null,
    var name: String? = null,
    var email: String? = null,
    var password: String? = null,
    var document: Double? = null,
    var cel_number: Double? = null
)

