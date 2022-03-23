package com.techfind.myapplication.server

data class User(
    var user_id: String? = null,
    var name: String? = null,
    var email: String? = null,
    var password: String? = null,
    var document: Int? = null,
    var cel_number: Int? = null
)

enum class Role {
    VENDEDOR, COMPRADOR, AMBOS
}


