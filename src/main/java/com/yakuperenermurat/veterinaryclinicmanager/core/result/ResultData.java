package com.yakuperenermurat.veterinaryclinicmanager.core.result;

import lombok.Getter;

@Getter
public class ResultData<T> extends Result {

    private T data;

    public ResultData(boolean status, String message, String code, T data) {
        // Veri içeren sonuç sınıfı, işlem durumunu, mesajını, kodunu ve veriyi tutar
        super(status, message, code);
        this.data = data;
    }
}