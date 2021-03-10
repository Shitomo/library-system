package com.morning.forum.demo.model

data class Author (
    var id: Int,
    var firstName: String,
    var lastName: String,

    val fullName : String = "$firstName $lastName",
)
