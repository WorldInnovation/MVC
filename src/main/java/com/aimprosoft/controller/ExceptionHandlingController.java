package com.aimprosoft.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@Controller
public class ExceptionHandlingController {

    @ResponseStatus(value = HttpStatus.CONFLICT,
            reason = "Data integrity violation")  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void conflict() {

    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public String databaseError() {

        return "sqlException";
    }

    @ExceptionHandler({Exception.class})
    public String exception(Exception e) {

        return "sqlException";
    }

}
