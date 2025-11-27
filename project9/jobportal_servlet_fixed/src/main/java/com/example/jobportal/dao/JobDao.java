package com.example.jobportal.dao;

import com.example.jobportal.model.Job;
import java.sql.*;
import java.util.*;

public class JobDao {
    public static List<Job> listAll() throws SQLException {
        List<Job> out = new ArrayList<>();
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM jobs ORDER BY posted_on DESC")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Job j = new Job();
                j.setId(rs.getLong("id"));
                j.setEmployerId(rs.getLong("employer_id"));
                j.setTitle(rs.getString("title"));
                j.setLocation(rs.getString("location"));
                j.setType(rs.getString("type"));
                int s = rs.getInt("salary"); if (!rs.wasNull()) j.setSalary(s);
                j.setDescription(rs.getString("description"));
                Timestamp t = rs.getTimestamp("posted_on"); if (t!=null) j.setPostedOn(new java.util.Date(t.getTime()));
                out.add(j);
            }
        }
        return out;
    }

    public static void create(Job job) throws SQLException {
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(
                     "INSERT INTO jobs(employer_id,title,location,type,salary,description,posted_on) VALUES(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, job.getEmployerId());
            ps.setString(2, job.getTitle());
            ps.setString(3, job.getLocation());
            ps.setString(4, job.getType());
            if (job.getSalary()!=null) ps.setInt(5, job.getSalary()); else ps.setNull(5, Types.INTEGER);
            ps.setString(6, job.getDescription());
            ps.setTimestamp(7, new Timestamp(new java.util.Date().getTime()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys(); if (rs.next()) job.setId(rs.getLong(1));
        }
    }
}
