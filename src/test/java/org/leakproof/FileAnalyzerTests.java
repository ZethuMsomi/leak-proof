package org.leakproof;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileAnalyzerTests {
    @TempDir
    Path tempDir;

    @Test
    void detectsAwsAccessKey() throws IOException {
        Path file = tempDir.resolve("aws.txt");

        Files.writeString(
                file,
                "normal line\nAKIA1234567890ABCDEF\nnormal line"
        );

        FileAnalyzer analyzer = new FileAnalyzer();

        List<ScanResult> results =
                analyzer.scanFile(file.toFile());

        assertEquals(1, results.size());
        assertEquals("AWS Keys:", results.get(0).getLeakName());
        assertEquals(2, results.get(0).getLineNumber());
        assertEquals(file.toFile(), results.get(0).getFile());
    }

    @Test
    void detectsPrivateKey() throws IOException {
        Path file = tempDir.resolve("private.txt");

        Files.writeString(
                file,
                "-----BEGIN RSA PRIVATE KEY-----"
        );

        FileAnalyzer analyzer = new FileAnalyzer();

        List<ScanResult> results =
                analyzer.scanFile(file.toFile());

        assertEquals(1, results.size());
        assertEquals("Private Key", results.get(0).getLeakName());
        assertEquals(1, results.get(0).getLineNumber());
    }

    @Test
    void detectsAwsSecretsManagerArn() throws IOException {
        Path file = tempDir.resolve("secret.txt");

        Files.writeString(
                file,
                "arn:aws:secretsmanager:us-east-1:123456789012:secret:database-password"
        );

        FileAnalyzer analyzer = new FileAnalyzer();

        List<ScanResult> results =
                analyzer.scanFile(file.toFile());

        assertEquals(1, results.size());
        assertEquals(
                "AWS Secrets Manager ARN",
                results.get(0).getLeakName()
        );
    }

//    @Test
//    void detectsSlackToken() throws IOException {
//        Path file = tempDir.resolve("slack.txt");
//
//        Files.writeString(
//                file,
//                "12345"
//        );
//
//        FileAnalyzer analyzer = new FileAnalyzer();
//
//        List<ScanResult> results =
//                analyzer.scanFile(file.toFile());
//
//        assertEquals(1, results.size());
//        assertEquals(
//                "Slack Token",
//                results.get(0).getLeakName()
//        );
//    }

    @Test
    void detectsGcpApiKey() throws IOException {
        Path file = tempDir.resolve("gcp.txt");

        Files.writeString(
                file,
                "AIza12345678901234567890123456789012345"
        );

        FileAnalyzer analyzer = new FileAnalyzer();

        List<ScanResult> results =
                analyzer.scanFile(file.toFile());

        assertEquals(1, results.size());
        assertEquals(
                "GCP API Key",
                results.get(0).getLeakName()
        );
    }

    @Test
    void returnsNoResultsForSafeFile() throws IOException {
        Path file = tempDir.resolve("safe.txt");

        Files.writeString(
                file,
                "Hello world\nThere are no secrets here."
        );

        FileAnalyzer analyzer = new FileAnalyzer();

        List<ScanResult> results =
                analyzer.scanFile(file.toFile());

        assertTrue(results.isEmpty());
    }
}