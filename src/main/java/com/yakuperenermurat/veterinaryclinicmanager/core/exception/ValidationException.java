package com.yakuperenermurat.veterinaryclinicmanager.core.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        // Doğrulama hatası durumunda fırlatılan özel hata sınıfı
        super(message);
    }
}