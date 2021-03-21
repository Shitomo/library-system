package com.morning.forum.demo.web.dto

import com.morning.forum.demo.domain.Book
import javax.validation.constraints.NotNull

data class RegisterBookRequestDto (
    /**
     * クライアントから入力される値が入るフィールドはNullになりうるので，公開しない
     * (メモ)Kotlinのdata classでは@NotNullではなく@field:NotNullとする
     */

    private var id: Int?,

    @field:NotNull(message = "titleは必須のパラメータ")
    private var title: String?,

    @field:NotNull(message = "authorsは必須のパラメータ")
    private var authors: String?,
    ) {

    /**
     * 外部にNullを漏らさないように，ここで詰め替えておく．
     */
    val validatedBook = Book (title=title ?: "", authors=authors ?: "")
}