package com.luigiceschim.aula.resources.exceptions;

import com.luigiceschim.aula.services.exceptions.DatabaseException;
import com.luigiceschim.aula.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        var status = HttpStatus.NOT_FOUND;

        StandardError error = new StandardError();

        error.setTimeStamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Recurso não encontrado");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {

        var status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError();

        error.setTimeStamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Exceção do banco de dados ");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);


    }
}