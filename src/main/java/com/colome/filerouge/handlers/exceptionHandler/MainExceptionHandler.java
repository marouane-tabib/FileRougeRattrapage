package com.colome.filerouge.handlers.exceptionHandler;

import com.colome.filerouge.handlers.response.ResponseMessage;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class MainExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e){
        Map<String, List<String>> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(FieldError::getField, Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<?> handleUnexpectedTypeException(UnexpectedTypeException e){
        //ResponseMessage responseMessage = new ResponseMessage(e);
        return ResponseEntity.badRequest().body(e);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleResourceNotFoundException(ResourceNotFoundException e){
        ResponseMessage responseMessage = new ResponseMessage(e.getMessage());
        return ResponseEntity.badRequest().body(responseMessage);
    }

    @ExceptionHandler(OperationException.class)
    public ResponseEntity<ResponseMessage> handleOperationException(OperationException e){
        ResponseMessage responseMessage = new ResponseMessage(e.getMessage());
        return ResponseEntity.badRequest().body(responseMessage);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ResponseMessage> handleEmailAlreadyExistsException(EmailAlreadyExistsException e){
        ResponseMessage responseMessage = new ResponseMessage(e.getMessage());
        return ResponseEntity.badRequest().body(responseMessage);
    }

    @ExceptionHandler(InvalidEmailOrPasswordException.class)
    public ResponseEntity<ResponseMessage> handleInvalidEmailOrPasswordException(InvalidEmailOrPasswordException e){
        ResponseMessage responseMessage = new ResponseMessage(e.getMessage());
        return ResponseEntity.badRequest().body(responseMessage);
    }

    @ExceptionHandler(PasswordsDoNotMatchException.class)
    public ResponseEntity<ResponseMessage> handlePasswordsDoNotMatchException(PasswordsDoNotMatchException e){
        ResponseMessage responseMessage = new ResponseMessage(e.getMessage());
        return ResponseEntity.badRequest().body(responseMessage);
    }
}
