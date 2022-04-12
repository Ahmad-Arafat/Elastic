package com.example.elasticsearch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
@ControllerAdvice//Class that handling multiple exceptions
public class ApiExceptionHandler {
@ExceptionHandler(value = {ApiRequestException.class})
public ResponseEntity<Object> handleAoiRequestException(ApiRequestException e) {
    //1.Create payload containing exception details

    HttpStatus badRequest = HttpStatus.NOT_FOUND;
    ApiException apiException= new ApiException (
            e.getMessage(),
            badRequest,
            ZonedDateTime.now(ZoneId.of("Z"))
    );
    //2.Return response entity
    return new ResponseEntity<>(apiException, badRequest);
}


}//to handle custom exceptions or existing exceptions
