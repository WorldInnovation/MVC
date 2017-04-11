package com.aimprosoft.controller;


import com.aimprosoft.exeption.ValidateExp;
import com.aimprosoft.model.Department;
import com.aimprosoft.service.DepartmentService;
import com.aimprosoft.util.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String editDepartment(@RequestParam("depID") long depId,Model model){
        Department department;
        try {
            department = departmentService.getDepartmentById(depId);
            model.addAttribute("department",department);
            model.addAttribute("depId",depId);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "editDep";
    }

    @RequestMapping(value = "/addDepartment", method=RequestMethod.GET)
    public String addDepartment(Model model){
            model.addAttribute("depID", null);
        return "editDep";
    }

    @RequestMapping(value = "/depDelete", method=RequestMethod.POST)
    public String depDelete(@RequestParam("depID") long depId){
        try {
            departmentService.getDepartmentById(depId);
            departmentService.deleteDepartment(depId);
            return "redirect:/";
        } catch (SQLException e) {
            return "sqlException";
        }

    }
    @RequestMapping(value = "/depSave", method=RequestMethod.POST)
    public String depSave(Department department, Model model){

        /*Department department = new Department();
        Long ldepID = FormatUtils.getLongFromStr(depID);*/
//        department.setName(depName);
       /* if (department.getId() != null) {
            department.setId(ldepID);
        }*/
        try {
            departmentService.saveOrUpdateDepartment(department);
            model.addAttribute("department",department);
            return "redirect:/";
        } catch (SQLException e) {
            return "sqlException";
        } catch (ValidateExp exp) {
           //model.addAttribute("depId",depID);
           model.addAttribute("errorMap",exp.getErrorMap());
           return "editDep";
        }
    }
}

