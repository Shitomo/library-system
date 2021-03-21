package com.morning.forum.demo.web.controller

import com.morning.forum.demo.exception.ValidationException
import com.morning.forum.demo.infrastructure.repository.LibraryRepository
import com.morning.forum.demo.web.dto.RegisterBookRequestDto
import com.morning.forum.demo.web.dto.RegisterBookResponseDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
class BookController {
    @Autowired
    lateinit var libraryRepository : LibraryRepository

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    fun registerBook(
        @RequestBody @Validated resisterBookRequestDto: RegisterBookRequestDto,
        bindingResult: BindingResult
    ): RegisterBookResponseDto {
        if (bindingResult.hasErrors()) {
            throw ValidationException(bindingResult.allErrors)
        }
        val book = resisterBookRequestDto.validatedBook;
        libraryRepository.register(book)
        return RegisterBookResponseDto("success")
    }
}