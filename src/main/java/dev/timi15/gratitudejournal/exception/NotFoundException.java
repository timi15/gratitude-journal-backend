package dev.timi15.gratitudejournal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final String message = "Entity not found.";

    public NotFoundException() {
        super(message);
    }
}
