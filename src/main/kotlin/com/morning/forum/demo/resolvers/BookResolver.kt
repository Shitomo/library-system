package com.morning.forum.demo.resolvers


import com.coxautodev.graphql.tools.GraphQLQueryResolver

import com.morning.forum.demo.types.Author
import com.morning.forum.demo.types.Book
import org.springframework.stereotype.Component

@Component
class BookResolver : GraphQLQueryResolver {
    fun bookById(bookId: String): Book {
        // 実際は何らかのデータストアからデータを読み込み返却するケースがほとんどだが、ここではダミー値を返却
        val author = Author("0001", "fName", "lName")
        return Book(bookId, "name", 900, author)
    }
}