package com.morning.forum.demo.exception

import org.springframework.dao.DataIntegrityViolationException
import java.lang.RuntimeException

class InternalServerException(message : String? = null) : RuntimeException(message)