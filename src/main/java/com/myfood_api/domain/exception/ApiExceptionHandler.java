package com.myfood_api.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Getter
    @AllArgsConstructor
    private static class ErrorDTO {
        private Integer codigo;
        private String erro;
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> entidadeEmUsoException(EntidadeEmUsoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiExceptionHandler.ErrorDTO(HttpStatus.NOT_FOUND.value(), e.getMessage())
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        body = new ApiExceptionHandler.ErrorDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }
}
