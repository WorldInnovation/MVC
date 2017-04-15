package com.aimprosoft.controller;


import com.aimprosoft.exeption.ValidateExp;
import com.aimprosoft.model.Department;
import com.aimprosoft.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class Departments extends ExceptionHandlingController{
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUsers(Model model) {
        List<Department> departments;
        try {
            departments = departmentService.getAll();
            model.addAttribute("departments", departments);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "depList";
    }

    @RequestMapping(value = "/editDepartment", method = RequestMethod.GET)
    public String editDepartment(@RequestParam(required = false) Long depID, Model model) {
        Department department;
        try {
            department = departmentService.getDepartmentById(depID);
            model.addAttribute("department", department);
            model.addAttribute("depId", depID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "editDep";
    }

    @RequestMapping(value = "/addDepartment", method = RequestMethod.GET)
    public String addDepartment(Model model) {
        model.addAttribute("depID", null);
        return "editDep";
    }

    @RequestMapping(value = "/depDelete", method = RequestMethod.POST)
    public String depDelete(@RequestParam("depID") Long depId) {
        try {
            departmentService.getDepartmentById(depId);
            departmentService.deleteDepartment(depId);
            return "redirect:/";
        } catch (SQLException e) {
            return "sqlException";
        }

    }

    @RequestMapping(value = "/depSave", method = RequestMethod.POST)
    public String depSave(Department department, Model model) {
        try {
            departmentService.saveOrUpdateDepartment(department);
            model.addAttribute("department", department);
            return "redirect:/";
        } catch (SQLException e) {
            return "sqlException";
        } catch (ValidateExp exp) {
            model.addAttribute("errorMap", exp.getErrorMap());
            return "editDep";
        }
    }
}

