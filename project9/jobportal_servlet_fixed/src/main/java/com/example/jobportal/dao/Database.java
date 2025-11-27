package com.example.jobportal.dao;

import java.sql.*;
import java.io.InputStream;

public class Database {
    private static final String URL = "jdbc:h2:./data/jobportal;AUTO_SERVER=TRUE";
    private static final String USER = "sa";
    private static final String PASS = "";

    static {
        try (Connection c = getConnection()) {
            try (InputStream in = Database.class.getResourceAsStream("/schema.sql")) {
                if (in != null) {
                    String sql = new String(in.readAllBytes());
                    for (String stmt : sql.split(";")) {
                        String t = stmt.trim();
                        if (!t.isEmpty()) c.createStatement().execute(t);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
