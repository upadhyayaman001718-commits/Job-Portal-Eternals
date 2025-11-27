package com.example.jobportal.dao;

import com.example.jobportal.model.Employer;
import com.example.jobportal.model.Seeker;
import java.sql.*;

public class UserDao {
    public static Employer createEmployer(Employer e) throws SQLException {
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement("INSERT INTO employers(company,email) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, e.getCompany());
            ps.setString(2, e.getEmail());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys(); if (rs.next()) e.setId(rs.getLong(1));
        }
        return e;
    }
    public static Seeker createSeeker(Seeker s) throws SQLException {
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement("INSERT INTO seekers(name,email,skills) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getSkills());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys(); if (rs.next()) s.setId(rs.getLong(1));
        }
        return s;
    }
}
