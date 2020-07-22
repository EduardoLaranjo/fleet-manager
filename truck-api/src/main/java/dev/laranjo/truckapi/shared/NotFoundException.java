package dev.laranjo.truckapi.shared;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public final class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
