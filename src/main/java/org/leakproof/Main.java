package org.leakproof;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Step 2: Choose a Target Directory
        // Replace this path with a real folder on your computer to test!
        // Example for Windows: "C:\\Users\\YourName\\Desktop\\TestFolder"
        // Example for Mac/Linux: "/Users/YourName/Desktop/TestFolder"

        // Using "./" means it will scan the current folder your project is in by default.
        String targetDirectory = "C:\\Users\\uzeth\\test-leak";

        System.out.println("Starting Leak-Proof Scanner...");
        System.out.println("Target Directory: " + targetDirectory);
        System.out.println("Scanning in progress...\n");

        // Step 3: Boot Up the Engine
        ProjectScanner scanner = new ProjectScanner();
        ReportGenerator reporter = new ReportGenerator();

        // Step 4: Execute the Scan
        List<ScanResult> findings = scanner.startScan(targetDirectory);

        // Step 5: Print the Report
        reporter.generateReport(findings);
    }
}

