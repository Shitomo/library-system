package com.morning.forum.demo.web.resolvers


import com.coxautodev.graphql.tools.GraphQLQueryResolver

import com.morning.forum.demo.model.Book
import com.morning.forum.demo.web.service.BookManageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class BookQueryResolver : GraphQLQueryResolver {
    @Autowired
    lateinit var bookManageService : BookManageService

    fun search(bookId: Int): Book {
        val book = bookManageService.searchById(bookId)
        return book
    }

}