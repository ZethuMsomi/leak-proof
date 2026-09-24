package org.leakproof.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class FindingRepository {

    private Connection connection;

    public FindingRepository(Connection connection){
        this.connection = connection;
    }


    public void save(List<FindingRecord> records){

        String sql = "INSERT INTO findings(file, line_number, leak_name, severity) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (FindingRecord record : records) {
                statement.setString(1, record.getFile().getAbsolutePath());
                statement.setInt(2, record.getLineNumber());
                statement.setString(3, record.getLeakName());
                statement.setString(4, record.getSeverity());

                statement.executeUpdate();
            }
            System.out.println("Findings saved to database.");
        } catch (SQLException e) {
            System.out.println("Could not save findings.");
        }
    }
}
