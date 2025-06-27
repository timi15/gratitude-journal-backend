package dev.timi15.gratitudejournal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class NoChangesDetectedException extends RuntimeException {

    private static final String message = "No changes detected to update.";

    public NoChangesDetectedException() {
        super(message);
    }
}
