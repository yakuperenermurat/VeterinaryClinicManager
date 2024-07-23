package com.yakuperenermurat.veterinaryclinicmanager.core.utilies;

import com.yakuperenermurat.veterinaryclinicmanager.core.result.Result;
import com.yakuperenermurat.veterinaryclinicmanager.core.result.ResultData;
import org.springframework.http.HttpStatus;

public class ResultHelper {

    // Sonuç ve hata mesajları oluşturmak için yardımcı metotlar

    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(true, Msg.CREATED, "201", data);
    }

    public static <T> ResultData<T> validateError(T data) {
        return new ResultData<>(false, Msg.VALIDATE_ERROR, "400", data);
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(true, Msg.OK, "200", data);
    }

    public static Result error() {
        return new Result(false, Msg.ERROR, "400");
    }

    public static Result ok() {
        return new Result(true, Msg.OK, "200");
    }

    public static Result notFoundError(String msg) {
        return new Result(false, msg, "404");
    }

    public static Result alreadyExistsError(String msg) {
        return new Result(false, msg, "409");
    }

    public static Result validationError(String message) {
        return new Result(false, message, "400");
    }

    public static Result internalServerError(String message) {
        return new Result(false, message, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    public static <T> ResultData<T> internalServerError(T data, String message) {
        return new ResultData<>(false, message, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), data);
    }
}