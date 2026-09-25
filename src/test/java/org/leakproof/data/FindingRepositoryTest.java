package org.leakproof.data;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FindingRepositoryTest {

    @Test
    void savesFindingToDatabase() throws Exception {
        Connection connection =
                DriverManager.getConnection("jdbc:sqlite::memory:");

        Statement statement = connection.createStatement();

        statement.execute(
                "CREATE TABLE findings (" +
                        "id INTEGER PRIMARY KEY NOT NULL, " +
                        "file TEXT NOT NULL, " +
                        "line_number INTEGER NOT NULL, " +
                        "leak_name TEXT NOT NULL, " +
                        "severity TEXT NOT NULL)"
        );

        FindingRecord record = new FindingRecord(
                new File("test.java"),
                10,
                "AWS Key",
                "HIGH"
        );

        FindingRepository repository =
                new FindingRepository(connection);

        repository.save(List.of(record));

        ResultSet result = statement.executeQuery(
                "SELECT * FROM findings"
        );

        assertTrue(result.next());
        assertEquals("AWS Key", result.getString("leak_name"));
        assertEquals("HIGH", result.getString("severity"));
        assertEquals(10, result.getInt("line_number"));
    }
}
