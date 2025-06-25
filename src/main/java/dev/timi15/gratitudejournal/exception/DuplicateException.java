package dev.timi15.gratitudejournal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateException extends RuntimeException {

    private static final String message = "Entity with this id already exists";

    public DuplicateException() {
        super(message);
    }
}
