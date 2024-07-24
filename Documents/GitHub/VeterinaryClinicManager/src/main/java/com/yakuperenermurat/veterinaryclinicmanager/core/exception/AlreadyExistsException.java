package com.yakuperenermurat.veterinaryclinicmanager.core.exception;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        // Zaten var olan kayıt için özel hata sınıfı
        super(message);
    }
}