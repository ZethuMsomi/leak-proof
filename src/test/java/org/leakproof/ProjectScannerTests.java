package org.leakproof;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectScannerTests {

    @TempDir
    Path tempDir;

    @Test
    void scansFilesInsideNestedDirectories() throws IOException {
        Path nestedDirectory =
                tempDir.resolve("folder1").resolve("folder2");

        Files.createDirectories(nestedDirectory);

        Path file =
                nestedDirectory.resolve("secret.txt");

        Files.writeString(
                file,
                "normal line\nAKIA1234567890ABCDEF"
        );

        ProjectScanner projectScanner =
                new ProjectScanner();

        List<ScanResult> results =
                projectScanner.startScan(tempDir.toString());

        assertEquals(1, results.size());
        assertEquals(
                "AWS Keys:",
                results.get(0).getLeakName()
        );
        assertEquals(
                2,
                results.get(0).getLineNumber()
        );
        assertEquals(
                "secret.txt",
                results.get(0).getFile().getName()
        );
    }

    @Test
    void returnsEmptyListWhenDirectoryDoesNotExist() {
        ProjectScanner projectScanner =
                new ProjectScanner();

        Path missingDirectory =
                tempDir.resolve("does-not-exist");

        List<ScanResult> results =
                projectScanner.startScan(
                        missingDirectory.toString()
                );

        assertTrue(results.isEmpty());
    }

    @Test
    void returnsEmptyListWhenPathIsNotDirectory()
            throws IOException {

        Path file = tempDir.resolve("file.txt");

        Files.writeString(
                file,
                "normal content"
        );

        ProjectScanner projectScanner =
                new ProjectScanner();

        List<ScanResult> results =
                projectScanner.startScan(file.toString());

        assertTrue(results.isEmpty());
    }
}