package br.com.devcave.cqrs.product.core.errorhandling

import org.axonframework.commandhandling.CommandExecutionException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ErrorHandlerController {
    @ExceptionHandler(value = [CommandExecutionException::class])
    fun handleCommandExecutionException(ex: CommandExecutionException, request: WebRequest): ResponseEntity<Any> {
        // This method catch exceptions from `@CommandHandler` methods
        return ResponseEntity(
            "Command execution exception: ${ex.message}",
            HttpHeaders(),
            HttpStatus.UNPROCESSABLE_ENTITY
        )
    }

    @ExceptionHandler(value = [IllegalStateException::class])
    fun handleIllegalStateException(ex: IllegalStateException, request: WebRequest): ResponseEntity<Any> {

        return ResponseEntity(ex.message, HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY)
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleException(ex: Exception, request: WebRequest): ResponseEntity<Any> {

        return ResponseEntity(ex.message, HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}