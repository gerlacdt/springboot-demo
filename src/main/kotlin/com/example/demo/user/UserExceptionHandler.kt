package com.example.demo.user

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class UserExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundEx(ex: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val err = ErrorResponse(HttpStatus.NOT_FOUND.value(),
                ex.message, listOf<String>())
        return ResponseEntity(err, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception::class)
    fun handleGlobalException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        val err = ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex?.message ?: "Unknown error", listOf<String>())
        return ResponseEntity(err, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    // @ExceptionHandler(MethodArgumentNotValidException::class)
    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val errors = mutableListOf<String>()
        for (err in ex.bindingResult.fieldErrors) {
            errors.add("${err.field} : ${err.defaultMessage}")
        }
        for (err in ex.bindingResult.globalErrors) {
            errors.add("${err.objectName} : ${err.defaultMessage}")
        }
        val err = ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.message, errors)
        return ResponseEntity(err, HttpStatus.BAD_REQUEST)
    }
}

data class ErrorResponse(val status: Int, val message: String, val errors: List<String>)
