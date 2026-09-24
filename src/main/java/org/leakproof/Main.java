package org.leakproof;
import org.leakproof.data.DataTransformer;
import org.leakproof.data.DatabaseManager;
import org.leakproof.data.FindingRecord;
import org.leakproof.data.FindingRepository;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Choose a Target Directory
        // Replace this path with a real folder on your computer to test!

        // Using "./" means it will scan the current folder your project is in by default.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Leak-Proof!");
        System.out.println("Input File Path: ");
        String inputPath = scanner.nextLine();

//// "C:\\Users\\uzeth\\test-leak"
//        String targetDirectory = inputPath;

        System.out.println("Starting Leak-Proof Scanner...");
        System.out.println("Target Directory: " + inputPath);
        System.out.println("Scanning in progress...\n");

        // Boot Up the Engine
        ProjectScanner projectScanner = new ProjectScanner();
        ReportGenerator reporter = new ReportGenerator();

        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.createTable();

        // EXTRACT:
        List<ScanResult> findings = projectScanner.startScan(inputPath);

        // TRANSFORM:
        DataTransformer transformer = new DataTransformer(findings);
        List<FindingRecord> records = transformer.transform();

        // LOAD:
        FindingRepository repository = new FindingRepository(databaseManager.getConnection());

        repository.save(records);

        // Print the Report:
        reporter.generateReport(findings);
    }
}

