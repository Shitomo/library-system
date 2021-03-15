package com.morning.forum.demo.web.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.morning.forum.demo.model.Author
import com.morning.forum.demo.model.Book
import javax.validation.constraints.NotNull

class BookRegisterWithAuthorRequestDto {
    @NotNull
    var id: Int? = null
    @NotNull
    var name: String? = null
    @NotNull
    var pageCount: Int? = null
    @NotNull
    var authorId: Int? = null
    @NotNull
    var firstName: String? = null
    @NotNull
    var lastName: String? = null

    fun convertToBook() :Book {
        val author = Author(authorId ?: 0, firstName ?: "", lastName ?: "")
        val book = Book(id ?: 0, name ?: "", pageCount ?: 0, author)
        return book
    }
}