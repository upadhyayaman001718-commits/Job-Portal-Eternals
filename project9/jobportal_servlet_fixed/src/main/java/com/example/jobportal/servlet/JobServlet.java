package com.example.jobportal.servlet;

import com.example.jobportal.dao.JobDao;
import com.example.jobportal.model.Job;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/jobs")
public class JobServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/postjob.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Job j = new Job();
            String empId = req.getParameter("employerId");
            if (empId!=null && !empId.isEmpty()) j.setEmployerId(Long.parseLong(empId));
            j.setTitle(req.getParameter("title"));
            j.setLocation(req.getParameter("location"));
            j.setType(req.getParameter("type"));
            String sal = req.getParameter("salary");
            if (sal!=null && !sal.isEmpty()) j.setSalary(Integer.parseInt(sal));
            j.setDescription(req.getParameter("description"));
            JobDao.create(j);
            resp.sendRedirect(req.getContextPath()+"/home");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
