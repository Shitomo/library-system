package com.morning.forum.demo.web.dto

import com.morning.forum.demo.domain.Book
import javax.validation.constraints.NotNull
/**
 * 本を蔵書として登録するリクエストのためのDTO
 */
data class RegisterBookRequestDto (
    /**
     * クライアントが入力する値を入れるフィールドはNullになりうるので，型はNullableにしておく.
     * 万が一にも外部から参照されないようにprivateをつけておく
     *
     * (メモ)data class内では，@field:アノテーション名 としないとバリデーションが働かない
     *
     * ex) × @NonNull
     *    〇 @field:NonNull
     */

    @field:NotNull(message = "titleは必須のパラメータ")
    private var title: String?,

    @field:NotNull(message = "authorsは必須のパラメータ")
    private var authors: String?,
    ) {

    /**
     * システム内で利用するオブジェクトはNullを許容しないので，非Nullな値に変換して渡す．
     */
    val validatedBook = Book (title=title ?: "", authors=authors ?: "")
}