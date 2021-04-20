package com.EREMEI.StackQueues;

public class FullDequeError extends StackOverflowError {
    public FullDequeError(String message) {
        super(message);
    }
    public FullDequeError() { }
}
