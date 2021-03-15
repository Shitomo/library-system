package com.morning.forum.demo.model

import com.morning.forum.demo.model.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired

data class Book(var id: Int?,
                var name: String,
                var pageCount: Int,
                var author: Author,) {

    val isExist = id != -1

    val register : (Book, BookRepository) -> Boolean = {
        book, bookRepository
        ->
        bookRepository.registerBook(book)
        true
    }

    fun searchById(id: Int, bookRepository : BookRepository): Book {
        return bookRepository.findById(id).get(0)
    }


}