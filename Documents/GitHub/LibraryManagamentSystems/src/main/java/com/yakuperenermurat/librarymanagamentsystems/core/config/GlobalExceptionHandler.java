package com.yakuperenermurat.librarymanagamentsystems.core.config;

import com.yakuperenermurat.librarymanagamentsystems.core.result.Result;
import com.yakuperenermurat.librarymanagamentsystems.core.result.ResultData;
import com.yakuperenermurat.librarymanagamentsystems.core.utilies.ResultHelper;
import com.yakuperenermurat.librarymanagamentsystems.core.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(ResultHelper.notFoundErorr(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handlerValidationErrors(MethodArgumentNotValidException e) {
        List<String> validationErrorList = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(ResultHelper.validateError(validationErrorList), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> handleAllExceptions(Exception e) {
        return new ResponseEntity<>(ResultHelper.internalServerError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}