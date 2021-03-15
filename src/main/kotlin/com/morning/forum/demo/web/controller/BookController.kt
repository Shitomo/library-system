package com.morning.forum.demo.web.controller

import com.morning.forum.demo.exception.BadRequestException
import com.morning.forum.demo.exception.InternalServerException
import com.morning.forum.demo.model.repository.BookRepository
import com.morning.forum.demo.web.dto.BookRegisterWithAuthorRequestDto
import com.morning.forum.demo.web.response.RegisterBookResponse
import com.morning.forum.demo.web.service.BookManageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
class BookController {
    @Autowired
    lateinit var bookManageService : BookManageService
    @Autowired
    lateinit var bookRepository : BookRepository

    // TODO バリデーションエラーをハンドリングして，適切なレスポンスを返す
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    fun registerBook(@RequestBody @Validated bookDto: BookRegisterWithAuthorRequestDto): RegisterBookResponse {
        val book = bookDto.convertToBook();
        book.register(book, bookRepository)
        return RegisterBookResponse("200", "success")
    }

    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun badRequest(e: BadRequestException) : Map<String, String?> =
        mapOf(Pair("status", "400"), Pair("message", e.message))

    @ExceptionHandler(BindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun validationError(e: BindException) : Map<String, String?> =
        mapOf(Pair("status", "400"), Pair("message", e.bindingResult.target.toString()))

    @ExceptionHandler(InternalServerException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun internalServerError(e: InternalServerException) : Map<String, String?> =
        mapOf(Pair("status", "500"), Pair("message", e.message))
}