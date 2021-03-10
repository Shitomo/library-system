package com.morning.forum.demo.model


data class Book(
    var id: Int,
    var name: String,
    var pageCount: Int,
    var author : Author
)