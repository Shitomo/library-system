package com.morning.forum.demo.infrastructure

import com.morning.forum.demo.exception.BadRequestException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import com.morning.forum.demo.infrastructure.repository.BookRepository
import com.morning.forum.demo.domain.Book
import org.springframework.dao.DuplicateKeyException


@Repository
class JpaBookRepository : BookRepository {
    private lateinit var jdbcTemplate: JdbcTemplate

    @Autowired
    fun JdbcBookRepository(jdbcTemplate: JdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate
    }

    override fun findByTitle(title : String): List<Book> {
        try {
            return jdbcTemplate.query(
                "SELECT id,title,authors FROM book WHERE title='${title}'"
            ) { resultSet, i ->
                val book = Book(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("authors")
                )
                book
            }
        } catch (e : Exception) {
            e.printStackTrace()
            throw Exception("サーバーが混雑しています")
        }
    }

    override fun findBook(book : Book): List<Book> {
        try {
            return jdbcTemplate.query(
                "SELECT id,title,authors FROM book WHERE title='${book.title}' AND authors='${book.authors}'"
            ) { resultSet, i ->
                Book(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("authors")
                )
            }
        } catch (e : Exception) {
            e.printStackTrace()
            throw Exception("サーバーが混雑しています")
        }
    }

    override fun register(book : Book) {
        try {
            jdbcTemplate.execute(
                "INSERT INTO book(title, authors) VALUES ('${book.title}', '${book.authors}')"
            )
            return
        } catch (e : DuplicateKeyException) {
            e.printStackTrace()
            throw BadRequestException("データが重複しています")
        } catch (e : Exception) {
            e.printStackTrace()
            throw Exception("サーバーが混雑しています")
        }
    }
}