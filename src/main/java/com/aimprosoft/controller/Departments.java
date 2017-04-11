package com.aimprosoft.controller;


import com.aimprosoft.exeption.ValidateExp;
import com.aimprosoft.model.Department;
import com.aimprosoft.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@org.springframework.stereotype.Controller
public class Departments {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUsers(Model model) {
        List<Department> departments ;
        try {
            departments = departmentService.getAll();
            model.addAttribute("departments",departments);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "depList";
    }

    @RequestMapping(value = "/editDepartment", method=RequestMethod.GET)
    @ResponseBody
    public String editDepartment(@RequestParam("depID") long depId){
        try {
            departmentService.getDepartmentById(depId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "editDep";
    }
    @RequestMapping(value = "/depDelete?{depId}", method=RequestMethod.POST)
    public String depDelete(@PathVariable Long depId, Model model){
        Department department ;
        try {
            department = departmentService.getDepartmentById(depId);
            departmentService.deleteDepartment(depId);
            model.addAttribute("department",department);
            return "redirect:/";
        } catch (SQLException e) {
            return "sqlException";
        }

    }
    @RequestMapping(value = " /depSave/", method=RequestMethod.POST)
    public String depSave(@PathVariable String depId,@PathVariable String depName, Model model){
        Department department = new Department();
        department.setName(depName);

        try {
            departmentService.saveOrUpdateDepartment(department);
            model.addAttribute("department",department);
            return "redirect:/";
        } catch (SQLException e) {
            return "sqlException";
        } catch (ValidateExp exp) {
           model.addAttribute("depId",depId);
           model.addAttribute("errorMap",exp.getErrorMap());
           return "editDep";
        }
    }

}
