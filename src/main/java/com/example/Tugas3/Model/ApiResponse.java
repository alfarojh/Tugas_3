package com.example.Tugas3.Model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ApiResponse {
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object object;

    public ApiResponse(String message, Object object) {
        this.message = message;
        this.object = object;
    }
    public ApiResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Object getObject() {
        return object;
    }
}
