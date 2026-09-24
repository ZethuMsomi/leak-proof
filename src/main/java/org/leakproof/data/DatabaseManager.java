package org.leakproof.data;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:leakproof.db");
            System.out.println("Database connected.");
        } catch (Exception e) {
            System.out.println("Database connection failed.");
        }
    }

    public void createTable(){
        try {
            String sql = "CREATE TABLE IF NOT EXISTS findings(" +
                    "id INTEGER PRIMARY KEY NOT NULL ," +
                    "file TEXT NOT NULL ," +
                    "line_number INTEGER NOT NULL ," +
                    "leak_name TEXT NOT NULL ," +
                    "severity TEXT NOT NULL)";

            connection.createStatement().execute(sql);
            System.out.println("Findings table is ready.");

        } catch (Exception e) {
            System.out.println("Could not create table.");
        }
    }
}
