package com.japp.list.exceptions;

public class SizeLimitExceededException extends Exception {

    public SizeLimitExceededException() {
        super("Size Limit Exceeded!");
    }

    public SizeLimitExceededException(String message) {
        super(message);
    }

    public SizeLimitExceededException(Throwable throwable) {
        super(throwable);
    }

}
