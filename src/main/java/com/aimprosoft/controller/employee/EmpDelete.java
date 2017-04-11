package com.aimprosoft.controller.employee;

import com.aimprosoft.controller.InternalController;
import com.aimprosoft.model.Employee;
import com.aimprosoft.service.EmployeeService;
import com.aimprosoft.util.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Controller("/EmpDelete")
public class EmpDelete implements InternalController {
    @Autowired
    private EmployeeService employeeService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException {
        String depID = req.getParameter("DepID");
        String empID = req.getParameter("EmpID");
        Long lEmpID = FormatUtils.getLongFromStr(empID);
        if (lEmpID != null) {
            Employee  employee = employeeService.getEmpByID(empID);
            employeeService.deleteEmployee(employee);
        }
        String sendParam = "?DepID=".concat(depID);
        resp.sendRedirect("/EmployeesList".concat(sendParam));
    }
}