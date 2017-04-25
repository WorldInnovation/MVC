package com.aimprosoft.controller;


import com.aimprosoft.exeption.DaoExp;
import com.aimprosoft.exeption.ValidateExp;
import com.aimprosoft.model.Employee;
import com.aimprosoft.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class EmployeesController extends ExceptionHandlingController {
    @Autowired
    private EmployeeService employeeService;

    @InitBinder()
    public void dataBinding(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/employeesList", method = RequestMethod.GET)
    public String getEmployees(@RequestParam("depID") Long depID, Model model) throws DaoExp {
        model.addAttribute("depID", depID);
        if (0 != depID) {
            List<Employee> employees = employeeService.listEmployee(depID);
            model.addAttribute("employees", employees);
        }
        return "empList";
    }

    @RequestMapping(value = "/employeeEdit", method = RequestMethod.GET)
    public String employeeEdit(@RequestParam("depID") Long depID, @RequestParam("empID") Long empID, Model model) throws DaoExp {
        model.addAttribute("depID", depID);
        if (null != empID) {
            Employee employee = employeeService.getEmpByID(empID);
            model.addAttribute("empID", employee.getId());
            model.addAttribute("employee", employee);
        }
        return "empEdit";
    }

    @RequestMapping(value = "/empDelete", method = RequestMethod.POST)
    public String empDelete(@RequestParam("depID") Long depID, @RequestParam("empID") Long empID) throws DaoExp {
        if (null != empID) {
            Employee employee = employeeService.getEmpByID(empID);
            employeeService.deleteEmployee(employee);
        }
        String sendParam = "?depID=".concat(String.valueOf(depID));
        return "redirect:/employeesList".concat(sendParam);
    }

    @RequestMapping(value = "/empSave", method = RequestMethod.POST)
    //@RequestBody
    public String empSave(@RequestParam("depID") Long depId, Employee employee, Model model) throws DaoExp {
        try {
            employeeService.updateEmployee(employee, depId);
        } catch (ValidateExp exp) {
            Long empId = employee.getId();

            model.addAttribute("depID", depId);
            model.addAttribute("empID", empId);
            model.addAttribute("errorMap", exp.getErrorMap());
            return "empEdit";
        }
        String sendParam = "?depID=".concat(String.valueOf(depId));
        return "redirect:/employeesList".concat(sendParam);
    }

}