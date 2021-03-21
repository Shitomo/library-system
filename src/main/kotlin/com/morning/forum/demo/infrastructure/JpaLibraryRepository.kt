package com.morning.forum.demo.infrastructure

import com.morning.forum.demo.exception.BadRequestException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import com.morning.forum.demo.domain.Book
import com.morning.forum.demo.domain.Library
import com.morning.forum.demo.infrastructure.repository.LibraryRepository
import org.springframework.dao.DuplicateKeyException


@Repository
class JpaLibraryRepository : LibraryRepository {
    private lateinit var jdbcTemplate: JdbcTemplate

    @Autowired
    fun JdbcLibraryRepository(jdbcTemplate: JdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate
    }

    @Autowired
    private lateinit var bookRepository: JpaBookRepository


    override fun register(book : Book) {
        try {
            val searchResult = bookRepository.findBook(book)
            if (searchResult.isEmpty()) {
                bookRepository.register(book)
            } else {
                val bookId = searchResult[0].id
                jdbcTemplate.execute(
                    "INSERT INTO library (book_id) VALUES (${bookId})"
                )
            }
        } catch (e : BadRequestException) {
            // 別のRepositoryで発生した例外は何もせずに再スローする
            throw e
        } catch (e : DuplicateKeyException) {
            e.printStackTrace()
            throw BadRequestException("データが重複しています")
        } catch (e : Exception) {
            e.printStackTrace()
            throw Exception("サーバー内部エラー")
        }
    }

    override fun findByTitle(title : String) : List<Library> {
        try {
            return jdbcTemplate.query(
                "SELECT library.id as library_id, is_borrowed, book.id as book_id, title, authors FROM book,library WHERE book.title='${title}' AND book.id = library.id"
            ) { resultSet, i ->
                val book = Book(
                    resultSet.getInt("book_id"),
                    resultSet.getString("title"),
                    resultSet.getString("authors")
                )
                Library(
                    resultSet.getInt("library_id"),
                    resultSet.getBoolean("is_borrowed"),
                    book
                )
            }
        } catch (e : BadRequestException) {
            // 別のRepositoryで発生した例外は何もせずに再スローする
            throw e
        } catch (e : DuplicateKeyException) {
            e.printStackTrace()
            throw BadRequestException("データが重複しています")
        } catch (e : Exception) {
            e.printStackTrace()
            throw Exception("サーバー内部エラー")
        }
    }
}