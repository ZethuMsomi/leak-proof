package org.leakproof.data;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatabaseManagerTest {

    @Test
    void connectsToDatabase() {
        DatabaseManager databaseManager = new DatabaseManager();

        assertNotNull(databaseManager.getConnection());
    }

    @Test
    void createsFindingsTable() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();

        databaseManager.createTable();

        Connection connection = databaseManager.getConnection();
        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery(
                "SELECT name FROM sqlite_master " +
                        "WHERE type='table' AND name='findings'"
        );

        assertTrue(result.next());
        assertTrue(result.getString("name").equals("findings"));
    }
}
