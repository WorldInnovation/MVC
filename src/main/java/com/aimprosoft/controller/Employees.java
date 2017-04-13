package com.aimprosoft.controller;


import com.aimprosoft.exeption.ValidateExp;
import com.aimprosoft.model.Employee;
import com.aimprosoft.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

@org.springframework.stereotype.Controller
public class Employees {
    @Autowired
    private EmployeeService employeeService;

    @InitBinder()
    public void dataBinding(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, true));
    }

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
        model.addAttribute("empID", "null");

        return "empEdit";
    }

    @RequestMapping(value = "/employeeEdit", method = RequestMethod.GET)
    public String employeeEdit(@RequestParam("depID") Long depID, @RequestParam("empID") Long empID, Model model) {
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
    public String empDelete(@RequestParam("depID") Long depID, @RequestParam("empID") Long empID) {
        try {
            if (null != empID) {
                Employee employee = employeeService.getEmpByID(empID);
                employeeService.deleteEmployee(employee);
            }
        } catch (SQLException e) {
            return "sqlException";
        }

        String sendParam = "?depID=".concat(String.valueOf(depID));
        return "redirect:/employeesList".concat(sendParam);
    }
    @RequestMapping(value = "/empSave", method = RequestMethod.POST)
    //@RequestBody Employee employee
    public String empSave(Employee employee, Model model, HttpServletRequest request) {
        request.getParameter(String.valueOf(employee));
        Long empID = null;
        Long depID = employee.getDepId();
        //if(result.hasErrors()) return "empEdit";
        try {
            employeeService.updateEmployee(employee);
        } catch (ValidateExp exp) {
            if(0 < employee.getId()){empID = employee.getId();}
            model.addAttribute("depID", depID);
            model.addAttribute("empID", empID);
            model.addAttribute("errorMap", exp.getErrorMap());
            return "empEdit";
        } catch (SQLException e) {
            return "sqlException";
        }
        String sendParam = "?depID=".concat(String.valueOf(depID));
        return "redirect:/employeesList".concat(sendParam);
    }

}