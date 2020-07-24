package dev.laranjo.truckapi.shared;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalRestErrorHandling {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException e) {
        ApiError apiError = new ApiError();
        apiError.setTime(LocalDateTime.now());
        apiError.setMessage(e.getMessage());
        apiError.setStatus(HttpStatus.NOT_FOUND.value());
        apiError.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

}
