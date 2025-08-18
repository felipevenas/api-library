package io.github.felipevenas.api_livraria.controllers.common;

import io.github.felipevenas.api_livraria.dto.ErrorField;
import io.github.felipevenas.api_livraria.dto.ErrorResponse;
import io.github.felipevenas.api_livraria.exceptions.DuplicatedRegistryException;
import io.github.felipevenas.api_livraria.exceptions.NotAllowedPermission;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<FieldError> fieldErros = e.getFieldErrors();
        List<ErrorField> listErrors = fieldErros
                .stream()
                .map(fe -> new ErrorField(fe.getField(), fe.getDefaultMessage()))
                .toList();

        return new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation error.",
                listErrors);
    }

    @ExceptionHandler(DuplicatedRegistryException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDuplicatedRegistryException(DuplicatedRegistryException e) {
        return ErrorResponse.conflictResponse(e.getMessage());
    }

    @ExceptionHandler(NotAllowedPermission.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotAllowedPermission(NotAllowedPermission e) {
        return ErrorResponse.defaultResponse("Operation is not allowed.");
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleOtherErros(RuntimeException e) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Unexpected error.",
                List.of());
    }

}
