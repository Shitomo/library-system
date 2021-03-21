package com.morning.forum.demo.infrastructure.repository
import com.morning.forum.demo.domain.Book
import com.morning.forum.demo.domain.Library


interface LibraryRepository {
    // 本を蔵書に追加.未登録の本のデータベースに登録された後に蔵書として登録される．
    fun register(book: Book)
    // 蔵書を本のタイトルから検索
    fun findByTitle(title: String) : List<Library>
}