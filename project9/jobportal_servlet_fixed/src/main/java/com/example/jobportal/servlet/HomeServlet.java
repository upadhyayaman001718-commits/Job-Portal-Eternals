package com.example.jobportal.servlet;

import com.example.jobportal.dao.JobDao;
import com.example.jobportal.model.Job;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Job> jobs = JobDao.listAll();
            req.setAttribute("jobs", jobs);
            req.getRequestDispatcher("/jobs.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
