package com.yakuperenermurat.veterinaryclinicmanager.core.exception;

public class ScheduleConflictException extends RuntimeException{

    public ScheduleConflictException(String message) {
        super(message);
    }

    public ScheduleConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}