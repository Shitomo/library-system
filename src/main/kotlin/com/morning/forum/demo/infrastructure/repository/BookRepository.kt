package com.morning.forum.demo.infrastructure.repository
import com.morning.forum.demo.model.Book

interface BookRepository {
    fun findById(id: Int) : List<Book>
}