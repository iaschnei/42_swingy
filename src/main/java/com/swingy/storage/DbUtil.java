package com.swingy.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil {
    private static final String DB_URL = "jdbc:h2:./swingy_db";
    private static final String USER = "sa";
    private static final String PASS = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void initDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Create hero table if it doesn't exist
            String sql = "CREATE TABLE IF NOT EXISTS HERO (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(10) NOT NULL," +
                    "class_name VARCHAR(10) NOT NULL," +
                    "hp INT NOT NULL," +
                    "max_hp INT NOT NULL," +
                    "def INT NOT NULL," +
                    "atk INT NOT NULL," +
                    "level INT NOT NULL," +
                    "exp INT NOT NULL," +
                    "x_pos INT NOT NULL," +
                    "y_pos INT NOT NULL," +
                    "power_second_chance BOOLEAN," +
                    "power_execute BOOLEAN," +
                    "power_escape BOOLEAN" +
                    ")";
            
            stmt.execute(sql);
            
        } catch (SQLException e) {
            System.err.println("Database initialization failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void shutdown() {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("SHUTDOWN");
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error shutting down database: " + e.getMessage());
        }
    }

}