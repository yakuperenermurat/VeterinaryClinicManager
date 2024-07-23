package com.yakuperenermurat.veterinaryclinicmanager.core.result;

import lombok.Getter;

@Getter
public class Result {

    private boolean status;
    private String message;
    private String code;

    public Result(boolean status, String message, String code) {
        // Sonuç sınıfı, işlem durumunu, mesajını ve kodunu tutar
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