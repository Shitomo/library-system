package com.morning.forum.demo.model

data class Author(var id: Int = -1,
                  var firstName: String,
                  var lastName: String,
                  ) {
    val fullName: String = "$firstName $lastName"
    val isExist = id != -1
}
