package com.monteiro.resultpattern.utils;

import java.util.function.Function;

import com.monteiro.resultpattern.models.AppError;

public class Result<T> {
    private final T value;
    private final AppError error;

    private Result(T value, AppError error) {
        this.value = value;
        this.error = error;
    }

    public T getValue() {
        return value;
    }

    public AppError getError() {
        return error;
    }

    public boolean isSuccess() {
        return error == null;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value, null);
    }

    public static <T> Result<T> failure(AppError error) {
        return new Result<>(null, error);
    }

    public <R> R map(Function<T, R> onSuccess, Function<AppError, R> onFailure) {
        return isSuccess() ? onSuccess.apply(value) : onFailure.apply(error);
    }

}
