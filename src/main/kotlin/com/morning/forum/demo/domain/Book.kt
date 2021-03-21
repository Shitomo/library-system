package com.morning.forum.demo.domain

import com.morning.forum.demo.infrastructure.repository.BookRepository


/**
 * 本
 */
data class Book(var id : Int = -1,
                var title: String,
                var authors: String,
                )