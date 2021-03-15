package com.morning.forum.demo.model.repository
import com.morning.forum.demo.model.Author
import com.morning.forum.demo.model.Book


interface BookRepository {
    fun findById(id: Int) : List<Book>
    fun registerBook(book: Book) : Int
    fun registerAuthor(author : Author) : Int
}