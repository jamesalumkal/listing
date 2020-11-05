package com.japp.list.exceptions;

public class UserListAlreadyExistsException extends RuntimeException {

    public UserListAlreadyExistsException() {
        super("User List already exists!");
    }

    public UserListAlreadyExistsException(String message) {
        super(message);
    }

    public UserListAlreadyExistsException(Throwable throwable) {
        super(throwable);
    }
}
