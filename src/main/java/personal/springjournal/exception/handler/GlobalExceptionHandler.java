package personal.springjournal.exception.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import personal.springjournal.exception.AlreadyExisted;
import personal.springjournal.exception.BadEmailPassword;
import personal.springjournal.exception.DataNotFound;
import personal.springjournal.exception.ServerError;
import personal.springjournal.exception.model.ExceptionResponse;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFound.class)
    public ResponseEntity<ExceptionResponse> dataNotFound(DataNotFound ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setError("NOT_FOUND");
        response.setMessage(ex.getMessage());
        response.setErrorAt(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> ConstraintViolationException(MethodArgumentNotValidException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setError("BAD_REQUEST");
        response.setMessage(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage());
        response.setErrorAt(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServerError.class)
    public ResponseEntity<ExceptionResponse> serverError(ServerError ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setError("SERVER_ERROR");
        response.setMessage(ex.getMessage());
        response.setErrorAt(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AlreadyExisted.class)
    public ResponseEntity<ExceptionResponse> resourceExisted(AlreadyExisted ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setError("CONFLICT");
        response.setMessage(ex.getMessage());
        response.setErrorAt(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadEmailPassword.class)
    public ResponseEntity<ExceptionResponse> passwordNotMatched(BadEmailPassword ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setError("WRONG_IDENTITY");
        response.setMessage(ex.getMessage());
        response.setErrorAt(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

}
