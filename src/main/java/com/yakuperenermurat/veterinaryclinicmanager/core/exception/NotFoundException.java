package com.yakuperenermurat.veterinaryclinicmanager.core.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        // Kayıt bulunamadığında fırlatılan özel hata sınıfı
        super(message);
    }
}