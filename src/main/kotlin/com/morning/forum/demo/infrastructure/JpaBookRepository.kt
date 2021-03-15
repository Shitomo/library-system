package com.morning.forum.demo.infrastructure

import com.morning.forum.demo.exception.BadRequestException
import com.morning.forum.demo.exception.InternalServerException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import com.morning.forum.demo.model.repository.BookRepository
import com.morning.forum.demo.model.Author
import com.morning.forum.demo.model.Book
import org.springframework.dao.DataAccessException
import org.springframework.dao.DuplicateKeyException
import javax.xml.crypto.Data


@Repository
class JpaBookRepository : BookRepository {
    private lateinit var jdbcTemplate: JdbcTemplate

    @Autowired
    fun JdbcBookRepository(jdbcTemplate: JdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate
    }

    override fun findById(id : Int): List<Book> {
        return jdbcTemplate.query(
            "SELECT book.name,book.page_num, author.id, author.first_name, author.last_name FROM book, author WHERE book.id=${id} AND book.id = author.book_id "
        ) { resultSet, i ->
                val author = Author(resultSet.getInt("id"),resultSet.getString("first_name"),resultSet.getString("last_name"))
                val book = Book(id, resultSet.getString("name"), resultSet.getInt("page_num"), author)
                book
        }
    }

    override fun registerBook(book : Book) : Int {
        try {
            getJdbcTemplate().execute(
                "INSERT INTO book (name, page_num) VALUES ('${book.name}', '${book.pageCount}')"
            )
            val bookId = jdbcTemplate.query(
                "SELECT id FROM book WHERE name='${book.name}' AND page_num = '${book.pageCount}'"
            ) { resultSet, i ->
                resultSet.getInt("id")
            }[0]
            val authorId = registerAuthor(book.author)
            getJdbcTemplate().execute(
                "INSERT INTO author_book_correspondence (book_id, author_id) VALUES ('${bookId}', '${authorId}')"
            )
            return bookId
        } catch (e : DuplicateKeyException) {
            e.printStackTrace()
            throw BadRequestException("1つ以上のデータが重複しています")
        } catch (e : Exception) {
            e.printStackTrace()
            throw InternalServerException("サーバーが混雑しています")
        }
    }

    override fun registerAuthor(author : Author) : Int {
        try {
            getJdbcTemplate().execute(
                    "INSERT INTO author (first_name, last_name) VALUES ('${author.firstName}', '${author.lastName}')"
                )
            val authorId = jdbcTemplate.query(
                "SELECT id FROM author WHERE first_name='${author.firstName}' AND last_name = '${author.lastName}'"
            ) { resultSet, i ->
                resultSet.getInt("id")
            }
            return authorId[0]
        } catch (e : DuplicateKeyException) {
            e.printStackTrace()
            throw BadRequestException("1つ以上のデータが重複しています")
        } catch (e : Exception) {
            e.printStackTrace()
            throw InternalServerException("サーバーが混雑しています")
        }
    }

    private fun isExist(author : Author) : Boolean {
        var authorId : List<Int> = getJdbcTemplate().query(
            "SELECT id FROM author WHERE id = ${author.id} AND first_name='${author.firstName}' AND last_name= '${author.lastName}'"
        ) { resultSet, i ->
            resultSet.getInt("id")
        }
        return authorId.isEmpty()
    }

    fun getJdbcTemplate() : JdbcTemplate {
        return this.jdbcTemplate
    }
}