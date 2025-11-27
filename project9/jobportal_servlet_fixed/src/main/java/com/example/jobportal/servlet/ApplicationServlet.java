package com.example.jobportal.servlet;

import com.example.jobportal.dao.Database;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;

@WebServlet("/apply")
public class ApplicationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long seekerId = Long.parseLong(req.getParameter("seekerId"));
        long jobId = Long.parseLong(req.getParameter("jobId"));
        String cover = req.getParameter("cover");
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement("INSERT INTO applications(seeker_id,job_id,cover_letter,applied_on,status) VALUES(?,?,?,?,?)")) {
            ps.setLong(1,seekerId); ps.setLong(2,jobId); ps.setString(3,cover);
            ps.setTimestamp(4,new Timestamp(new java.util.Date().getTime())); ps.setString(5,"SUBMITTED");
            ps.executeUpdate();
            resp.sendRedirect(req.getContextPath()+"/home");
        } catch (Exception e) { throw new ServletException(e); }
    }
}
