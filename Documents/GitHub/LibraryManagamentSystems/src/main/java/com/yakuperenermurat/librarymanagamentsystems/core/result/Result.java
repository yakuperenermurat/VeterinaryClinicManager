package com.yakuperenermurat.librarymanagamentsystems.core.result;

import lombok.Getter;

@Getter
public class Result {
    private boolean status;
    private String message;
    private String code;

    public Result(boolean status, String message, String code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public Result(boolean status, String message, int value) {
        this.status = status;
        this.message = message;
        this.code = String.valueOf(value);
    }
}