package com.aimprosoft.controller.department;

import com.aimprosoft.controller.InternalController;
import com.aimprosoft.model.Department;
import com.aimprosoft.service.DepartmentService;
import com.aimprosoft.util.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class DepDelete implements InternalController {
    @Autowired
    private DepartmentService departmentService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException {
        String strDepID = req.getParameter("DepID");
        Long depId = FormatUtils.getLongFromStr(strDepID);
        if (depId != null) {
            Department department = departmentService.getDepartmentById(strDepID);
            departmentService.deleteDepartment(department);
        }
        resp.sendRedirect("/");
    }
}