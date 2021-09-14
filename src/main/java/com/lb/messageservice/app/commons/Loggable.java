package com.lb.messageservice.app.commons;

import com.lb.messageservice.app.config.RequestCorrelation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Loggable {
    default void info(String message, Class<?> clazz) {
        Logger log = LoggerFactory.getLogger(clazz);
        log.info(this.message(message));
    }

    default void error(String message, Class<?> clazz) {
        Logger log = LoggerFactory.getLogger(clazz);
        log.error(this.message(message));
    }

    private String message(String message) {
        return message.concat(" - ").concat(RequestCorrelation.getId());
    }
}
