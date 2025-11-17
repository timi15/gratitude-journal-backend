package hu.unideb.inf.gratitude_journal.configuration;

import hu.unideb.inf.gratitude_journal.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new LinkedHashMap<>();
        ex.getBindingResult().getAllErrors().forEach(err -> {
            String field = (err instanceof FieldError fe)
                    ? fe.getField()
                    : err.getObjectName();

            errors.computeIfAbsent(field,
                    (k) -> new ArrayList<>()).add(err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(Map.of(
                "title", "constraint violation",
                "status", "400",
                "errors", errors,
                "timestamp", System.currentTimeMillis()
        ));

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleGratitudeNotFound(
            NotFoundException ex) {

        return ResponseEntity.status(404).body(Map.of(
                "title", "not found",
                "status", "404",
                "message", ex.getMessage(),
                "timestamp", System.currentTimeMillis()
        ));
    }

    @ExceptionHandler(ForbiddenOperationException.class)
    public ResponseEntity<Map<String, Object>> handleForbidden(ForbiddenOperationException ex) {

        return ResponseEntity.status(403).body(Map.of(
                "title", "forbidden",
                "status", "403",
                "message", ex.getMessage(),
                "timestamp", System.currentTimeMillis()
        ));
    }

    @ExceptionHandler(UsernameAlreadyExistException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateUsername(UsernameAlreadyExistException ex) {

        return ResponseEntity.status(409).body(Map.of(
                "title", "duplicate username",
                "status", "409",
                "message", ex.getMessage(),
                "timestamp", System.currentTimeMillis()
        ));
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<Map<String, Object>> handleEmailAlreadyUsed(EmailAlreadyUsedException ex) {

        return ResponseEntity.status(409).body(Map.of(
                "title", "email already in use",
                "status", "409",
                "message", ex.getMessage(),
                "timestamp", System.currentTimeMillis()
        ));
    }

    @ExceptionHandler(DateAlreadyExistException.class)
    public ResponseEntity<Map<String, Object>> handleDateAlreadyExist(DateAlreadyExistException ex) {

        return ResponseEntity.status(409).body(Map.of(
                "title", "date already exist",
                "status", "409",
                "message", ex.getMessage(),
                "timestamp", System.currentTimeMillis()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralError(Exception ex) {
        return ResponseEntity.status(500).body(Map.of(
                "title", "internal server error",
                "status", 500,
                "message", ex.getMessage()
        ));
    }
}