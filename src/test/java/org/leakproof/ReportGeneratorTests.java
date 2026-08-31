package org.leakproof;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReportGeneratorTests {
    private ByteArrayOutputStream output;
    private PrintStream originalOutput;

    @BeforeEach
    void setUp() {
        originalOutput = System.out;
        output = new ByteArrayOutputStream();

        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOutput);
    }

    @Test
    void printsSuccessMessageWhenNoLeaksAreFound() {
        ReportGenerator generator =
                new ReportGenerator();

        generator.generateReport(
                new ArrayList<>()
        );

        String result = output.toString();

        assertTrue(
                result.contains(
                        "Scan complete. No leaks found. Great Job!"
                )
        );
    }

    @Test
    void printsLeakInformationWhenLeakIsFound() {
        ReportGenerator generator =
                new ReportGenerator();

        ScanResult scanResult =
                new ScanResult(
                        new File("secret.txt"),
                        5,
                        "AWS Keys:"
                );

        List<ScanResult> results =
                List.of(scanResult);

        generator.generateReport(results);

        String outputText = output.toString();

        assertTrue(
                outputText.contains("CRITICAL: LEAKS FOUND")
        );

        assertTrue(
                outputText.contains("Number of leaks found: 1")
        );

        assertTrue(
                outputText.contains("AWS Keys:")
        );

        assertTrue(
                outputText.contains("Line # :5")
        );
    }
}
