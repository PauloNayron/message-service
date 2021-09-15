package com.lb.messageservice.domain.exception;

public class UnknownChannelException extends RuntimeException {
    public UnknownChannelException(String message) { super(message); }
}
