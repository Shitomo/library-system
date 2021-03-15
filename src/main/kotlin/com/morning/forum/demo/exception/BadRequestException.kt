package com.morning.forum.demo.exception

import org.springframework.dao.DataIntegrityViolationException
import java.lang.RuntimeException

class BadRequestException(message : String? = null) : RuntimeException(message)