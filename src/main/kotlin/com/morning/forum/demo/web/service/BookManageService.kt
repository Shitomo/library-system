package com.morning.forum.demo.web.service

import com.morning.forum.demo.infrastructure.repository.BookRepository
import com.morning.forum.demo.model.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookManageService {
    @Autowired
    private lateinit var bookRepository: BookRepository

    fun searchById(id : Int) : Book {
        return bookRepository.findById(id).get(0)
    }
}