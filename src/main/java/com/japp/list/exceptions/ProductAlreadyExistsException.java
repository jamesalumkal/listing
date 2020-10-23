package com.japp.list.exceptions;

public class ProductAlreadyExistsException extends Exception {

    public ProductAlreadyExistsException() {
        super("Product already exists!");
    }

    public ProductAlreadyExistsException(String message) {
        super(message);
    }

    public ProductAlreadyExistsException(Throwable throwable) {
        super(throwable);
    }
}
