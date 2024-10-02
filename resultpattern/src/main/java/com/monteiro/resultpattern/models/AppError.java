package com.monteiro.resultpattern.models;

public class AppError {
    private final String message;
    private final String code;

    public AppError(String message, String code) {
        this.message = message;
        this.code = code;
    }

    // Getters
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}