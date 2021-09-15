package com.lb.messageservice.app.handler;

public record ErrorResponse(
        String status,
        String message
) {}
