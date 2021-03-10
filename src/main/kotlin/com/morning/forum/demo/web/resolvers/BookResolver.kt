package com.morning.forum.demo.web.resolvers


import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.morning.forum.demo.infrastructure.repository.BookRepository

import com.morning.forum.demo.model.Author
import com.morning.forum.demo.model.Book
import com.morning.forum.demo.web.service.BookManageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component



@Component
class BookResolver : GraphQLQueryResolver {
    @Autowired
    lateinit var bookManageService : BookManageService

    fun search(bookId: Int): Book {
        // 実際は何らかのデータストアからデータを読み込み返却するケースがほとんどだが、ここではダミー値を返却
        val book = bookManageService.searchById(bookId)
        return book
    }
}