package com.morning.forum.demo.infrastructure.repository
import com.morning.forum.demo.domain.Book


interface BookRepository {
    // 本をタイトルから検索
    fun findByTitle(title: String) : List<Book>
    // 全ての情報が一致する本を検索
    fun findBook(book: Book) : List<Book>
    // 本を新規登録
    fun register(book: Book)
}