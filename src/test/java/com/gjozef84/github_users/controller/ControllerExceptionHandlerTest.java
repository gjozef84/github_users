package com.gjozef84.github_users.controller;

import com.gjozef84.github_users.dto.UserDataDTO;
import com.gjozef84.github_users.exceptions.ApplicationException;
import com.gjozef84.github_users.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ControllerExceptionHandlerTest {

    private final ControllerExceptionHandler underTest = new ControllerExceptionHandler();

    @Test
    void testResourceNotFoundExceptionHandler() {
        ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException(UserDataDTO.class, "login", "X");
        final ResponseEntity<String> actual = underTest.resourceNotFoundExceptionHandler(resourceNotFoundException);
        final ResponseEntity<String> expected = new ResponseEntity<>("Resource UserDataDTO with login = X not found", HttpStatus.NOT_FOUND);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testApplicationExceptionHandler() {
        ApplicationException applicationException = new ApplicationException("message");
        final ResponseEntity<String> actual = underTest.applicationExceptionHandler(applicationException);
        final ResponseEntity<String> expected = new ResponseEntity<>("message", HttpStatus.BAD_REQUEST);

        Assertions.assertEquals(expected, actual);
    }
}