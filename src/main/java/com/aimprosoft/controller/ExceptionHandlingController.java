package com.aimprosoft.controller;

import com.aimprosoft.exeption.DaoExp;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;

@Controller
public class ExceptionHandlingController {

    @ExceptionHandler({DaoExp.class})
    public String databaseError(Exception e, Model model) {
        model.addAttribute("sqlError",e);
        return "sqlException";
    }

    @ExceptionHandler({Exception.class})
    public String exception(Exception e, Model model) {
        model.addAttribute("error", e);
        return "exception";
    }
}
/*
    @ResponseStatus(value = HttpStatus.CONFLICT,
            reason = "Data integrity violation")  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String dataIntegrity(Model model) {
        model.addAttribute("sqlError","Data integrity violation");
        return "sqlException";
    }*/
