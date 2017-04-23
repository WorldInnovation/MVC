package com.aimprosoft.controller;


import com.aimprosoft.exeption.DaoExp;
import com.aimprosoft.exeption.ValidateExp;
import com.aimprosoft.model.Department;
import com.aimprosoft.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class Departments extends ExceptionHandlingController {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = {"/", "/DepartmentsList"}, method = RequestMethod.GET)
    public String getUsers(Model model) throws DaoExp {
        List<Department> departments;
        departments = departmentService.getAll();
        model.addAttribute("departments", departments);
        return "depList";
    }

    @RequestMapping(value = "/editDepartment", method = RequestMethod.GET)
    public String editDepartment(@RequestParam(required = false) Long depID, Model model) throws DaoExp {
        Department department;
        department = departmentService.getDepartmentById(depID);
        model.addAttribute("department", department);
        model.addAttribute("depId", depID);
        return "editDep";
    }

    @RequestMapping(value = "/addDepartment", method = RequestMethod.GET)
    public String addDepartment(Model model) {
        model.addAttribute("depID", null);
        return "editDep";
    }

    @RequestMapping(value = "/depDelete", method = RequestMethod.POST)
    public String depDelete(@RequestParam("depID") Long depId) throws DaoExp {
        Department department = departmentService.getDepartmentById(depId);
        departmentService.deleteDepartment(depId);
        return "redirect:/";

    }

    @RequestMapping(value = "/depSave", method = RequestMethod.POST)
    public String depSave(Department department, Model model) throws DaoExp {
        try {
            departmentService.saveOrUpdateDepartment(department);
            model.addAttribute("department", department);
            return "redirect:/";
        } catch (ValidateExp exp) {
            model.addAttribute("errorMap", exp.getErrorMap());
            return "editDep";
        }
    }
}

