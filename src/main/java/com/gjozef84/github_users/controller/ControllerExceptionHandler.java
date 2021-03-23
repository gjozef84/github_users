package com.gjozef84.github_users.controller;

import com.gjozef84.github_users.exceptions.ApplicationException;
import com.gjozef84.github_users.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity<String> applicationExceptionHandler(ApplicationException ae) {
        log.debug("called applicationExceptionHandler", ae);
        ResponseEntity<String> result = new ResponseEntity<>(ae.getMessage(), HttpStatus.BAD_REQUEST);
        log.debug("about to return applicationExceptionHandler :: result='{}'", result);
        return result;
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> resourceNotFoundExceptionHandler(ResourceNotFoundException rnfe) {
        log.debug("called resourceNotFoundExceptionHandler", rnfe);
        ResponseEntity<String> result = new ResponseEntity<>(rnfe.getMessage(), HttpStatus.NOT_FOUND);
        log.debug("about to return resourceNotFoundExceptionHandler :: result='{}'", result);
        return result;
    }
}