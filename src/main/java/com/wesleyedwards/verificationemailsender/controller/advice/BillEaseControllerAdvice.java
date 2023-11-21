package com.wesleyedwards.verificationemailsender.controller.advice;

import com.wesleyedwards.verificationemailsender.dtos.ErrorDto;
import com.wesleyedwards.verificationemailsender.exceptions.AlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ControllerAdvice(basePackages = { "com.wesleyedwards.BillEase.controllers" })
public class BillEaseControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorDto handleAlreadyExistsException(AlreadyExistsException alreadyExistsException) {
        return new ErrorDto(alreadyExistsException.getMessage());
    }
}
