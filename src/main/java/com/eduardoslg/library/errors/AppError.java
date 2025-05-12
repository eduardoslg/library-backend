package com.eduardoslg.library.errors;

public class AppError extends RuntimeException {
    public AppError(String message) {
        super(message);
    }
}
