package com.lb.messageservice.app.config;

public class RequestCorrelation {
    public static final String CORRELATION_ID_HEADER = "correlationId";
    private static final ThreadLocal<String> id = new ThreadLocal<String>();

    public static void setId(String correlationId) { id.set(correlationId); }

    public static String getId() { return CORRELATION_ID_HEADER.concat(":").concat(id.get()); }
}
