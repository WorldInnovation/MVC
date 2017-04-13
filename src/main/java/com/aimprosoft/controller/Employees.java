package com.aimprosoft.controller;


import com.aimprosoft.exeption.ValidateExp;
import com.aimprosoft.model.Employee;
import com.aimprosoft.service.EmployeeService;
import com.aimprosoft.util.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@org.springframework.stereotype.Controller
public class Employees {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employeesList", method = RequestMethod.GET)
    public String getEmployees(@RequestParam("depID") Long depID, Model model) {
        model.addAttribute("depID", depID);
        if (0 != depID) {
            try {
                List<Employee> employees = employeeService.listEmployee(depID);
                model.addAttribute("employees", employees);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "empList";
    }
    @RequestMapping(value = "/addEmployee", method=RequestMethod.GET)
    public String addDepartment(@RequestParam("depID") Long depID, Model model){
        model.addAttribute("depID", depID);

        return "empEdit";
    }
/*
    @RequestMapping(value = "/empEdit", method = RequestMethod.GET)
    public String empEdit(@RequestParam("depID") long depID, @RequestParam("empID") long empID, Model model) {
        model.addAttribute("depID", depID);

        try {
            if (0 != empID) {
                Employee employee = employeeService.getEmpByID(empID);
                model.addAttribute("empID", empID);
                model.addAttribute("employee", employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "empEdit";

    }

    @RequestMapping(value = "/empDelete", method = RequestMethod.POST)
    public String empDelete(@RequestParam("depID") long depID, @RequestParam("empID") long empID, Model model) {
       // Long lEmpID = FormatUtils.getLongFromStr(empID);
        try {
            if (lEmpID != null) {
                Employee employee = employeeService.getEmpByID(empID);
                employeeService.deleteEmployee(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sendParam = "?depID=".concat(String.valueOf(depID));
        return "redirect:/employeesList".concat(sendParam);
    }

    @RequestMapping(value = "/empSave", method = RequestMethod.POST)
    public String empSave(Employee employee, Model model) {
        Long depID = employee.getDepId();
        Long empID = employee.getId();
        try {
            employeeService.updateEmployee(employee);
        } catch (ValidateExp exp) {
            model.addAttribute("depID", depID);
            model.addAttribute("empID", empID);
            model.addAttribute("errorMap", exp.getErrorMap());
            return "empEdit";
        }
        String sendParam = "?depID=".concat(String.valueOf(depID));
        return "redirect:/employeesList".concat(sendParam);
    }*/

}