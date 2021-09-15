package com.lb.messageservice.app.handler;

import com.lb.messageservice.app.commons.Loggable;
import com.lb.messageservice.domain.exception.ReportNotFoundException;
import com.lb.messageservice.domain.exception.StatusCannotBeUpdateException;
import com.lb.messageservice.domain.exception.UnknownChannelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController implements Loggable {

    @ExceptionHandler(ReportNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleReportNotFoundException(ReportNotFoundException ex) {
        info(ex.getMessage(), ReportNotFoundException.class);
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(status.toString(), ex.getMessage()));
    }

    @ExceptionHandler(StatusCannotBeUpdateException.class)
    public ResponseEntity<ErrorResponse> handleStatusCannotBeUpdateException(StatusCannotBeUpdateException ex) {
        info(ex.getMessage(), StatusCannotBeUpdateException.class);
        var status = HttpStatus.CONFLICT;
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(status.toString(), ex.getMessage()));
    }

    @ExceptionHandler(UnknownChannelException.class)
    public ResponseEntity<ErrorResponse> handleUnknownChannelException(UnknownChannelException ex) {
        info(ex.getMessage(), UnknownChannelException.class);
        var status = HttpStatus.NOT_ACCEPTABLE;
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(status.toString(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        info(ex.getMessage(), Exception.class);
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(status.toString(), ex.getMessage()));
    }
}
