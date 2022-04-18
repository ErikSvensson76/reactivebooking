package com.example.reactivebooking.controller;

import com.example.reactivebooking.model.error.ErrorResponse;
import com.example.reactivebooking.model.error.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApplicationExceptionHandler {

    public static final String VALIDATIONS_FAILED = "One or several validations failed";

    private ErrorResponse build(String message, HttpStatus status, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(status.name());
        errorResponse.setStatus(status.value());
        errorResponse.setTimeStamp(LocalDateTime.now());
        errorResponse.setPath(request.getDescription(false));
        errorResponse.setMessage(message);
        return errorResponse;
    }

    private ValidationErrorResponse build(MethodArgumentNotValidException exception, WebRequest request){
        ValidationErrorResponse errorResponse = new ValidationErrorResponse();
        errorResponse.setError(HttpStatus.BAD_REQUEST.name());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setTimeStamp(LocalDateTime.now());
        errorResponse.setPath(request.getDescription(false));
        errorResponse.setMessage(VALIDATIONS_FAILED);
        List<String> fields = exception.getBindingResult().getFieldErrors().stream()
                .distinct()
                .map(FieldError::getField)
                .collect(Collectors.toList());

        Map<String, List<String>> errors = new HashMap<>();

        for(var field : fields){
            List<String> list = new ArrayList<>();
            for(var fieldError : exception.getFieldErrors(field)){
                list.add(fieldError.getDefaultMessage());
            }
            errors.put(field, list);
        }
        errorResponse.setErrors(errors);
        return errorResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    Mono<ResponseEntity<ValidationErrorResponse>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request){
        return Mono.just(build(exception, request))
                .map(validationErrorResponse -> ResponseEntity.badRequest().body(validationErrorResponse));
    }

}
