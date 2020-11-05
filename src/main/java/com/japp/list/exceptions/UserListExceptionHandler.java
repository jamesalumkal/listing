package com.japp.list.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class UserListExceptionHandler {

    @ExceptionHandler(SizeLimitExceededException.class)
    public ResponseEntity<?> handleSizeLimitExceededExcepton(SizeLimitExceededException e, WebRequest webRequest) {
        UserListErrorObject errorObject = new UserListErrorObject(
                new Date(),
                e.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity(errorObject, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<?> handleProductAlreadyExistsException(ProductAlreadyExistsException e, WebRequest webRequest) {
        UserListErrorObject errorObject = new UserListErrorObject(
                new Date(),
                e.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity(errorObject, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(UserListAlreadyExistsException.class)
    public ResponseEntity<?> handleUserListAlreadyExistsException(UserListAlreadyExistsException e, WebRequest webRequest) {
        UserListErrorObject errorObject = new UserListErrorObject(
                new Date(),
                e.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity(errorObject, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalExcepton(Throwable e, WebRequest webRequest) {
        UserListErrorObject errorObject = new UserListErrorObject(
                new Date(),
                e.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

