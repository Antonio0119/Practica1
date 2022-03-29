package com.techfind.myapplication.server

import java.io.Serializable

data class ServiceServer (
    var id: String? = null,
    var category: String? = null,
    var description: String? = null,
    var experience: Int? = null,
    var price: Int? = null,
    var score: Int? = null,
) : Serializable
