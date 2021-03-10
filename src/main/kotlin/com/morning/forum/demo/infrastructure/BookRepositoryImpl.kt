package com.morning.forum.demo.infrastructure

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import com.morning.forum.demo.infrastructure.repository.BookRepository
import com.morning.forum.demo.model.Author
import com.morning.forum.demo.model.Book


@Repository
class BookRepositoryImpl : BookRepository {
    private lateinit var jdbcTemplate: JdbcTemplate

    @Autowired
    fun JdbcBookRepository(jdbcTemplate: JdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate
    }

    override fun findById(id : Int): List<Book> {
        return getJdbcTemplate().query(
            "SELECT book.name,book.page_num, author.id, author.first_name, author.last_name FROM book, author WHERE book.id=${id} AND book.id = author.book_id "
        ) { resultSet, i ->
                val author = Author(resultSet.getInt("id"),resultSet.getString("first_name"),resultSet.getString("last_name"))
                val book = Book(id, resultSet.getString("name"), resultSet.getInt("page_num"), author)
                book
        }
    }
    fun getJdbcTemplate(): JdbcTemplate {
        return jdbcTemplate
    }
}