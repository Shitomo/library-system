package com.morning.forum.demo.exception

import org.springframework.validation.ObjectError
import java.lang.RuntimeException

class ValidationException(val errors : List<ObjectError>) : RuntimeException() {
    fun getErrorDetail() : String {
        var errorsSummary : String = ""
        for (error in this.errors) {
            errorsSummary += error.toString()
        }
        return errorsSummary
    }
}