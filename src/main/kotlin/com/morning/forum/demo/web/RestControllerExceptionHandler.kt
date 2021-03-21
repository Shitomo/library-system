package com.morning.forum.demo.web

import com.morning.forum.demo.exception.BadRequestException
import com.morning.forum.demo.exception.ValidationException
import com.morning.forum.demo.web.dto.ErrorResponseDto
import javassist.NotFoundException
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.http.HttpStatus
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@RestControllerAdvice
class RestControllerExceptionHandler : ResponseEntityExceptionHandler() {

    /**
     * クライアント要因のエラーをキャッチする
     */
    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun badRequest(e: BadRequestException, request: WebRequest) : ErrorResponseDto{
        return ErrorResponseDto(e.toString(), request.contextPath)
    }

    /**
     * クライアント要因のエラーの中でもバリデーションエラーをキャッチする．
     */
    @ExceptionHandler(ValidationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun validationError(e: ValidationException, request: WebRequest): ErrorResponseDto {
        return ErrorResponseDto(e.getErrorDetail(), request.toString())
    }

    /**
     * 上記で補足できなかったエラーをキャッチする．
     * おそらく実装ミスや考慮漏れによるもの.
     */
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun internalServerError(e: Exception, request: WebRequest) : ErrorResponseDto {
        return ErrorResponseDto(e.toString(), request.toString())
    }
}