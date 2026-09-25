package org.leakproof.data;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindingRecordTest {

    @Test
    void storesFile() {
        File file = new File("test.java");

        FindingRecord record = new FindingRecord(
                file,
                10,
                "AWS Key",
                "HIGH"
        );

        assertEquals(file, record.getFile());
    }

    @Test
    void storesLineNumber() {
        FindingRecord record = new FindingRecord(
                new File("test.java"),
                10,
                "AWS Key",
                "HIGH"
        );

        assertEquals(10, record.getLineNumber());
    }

    @Test
    void storesLeakName() {
        FindingRecord record = new FindingRecord(
                new File("test.java"),
                10,
                "AWS Key",
                "HIGH"
        );

        assertEquals("AWS Key", record.getLeakName());
    }

    @Test
    void storesSeverity() {
        FindingRecord record = new FindingRecord(
                new File("test.java"),
                10,
                "AWS Key",
                "HIGH"
        );

        assertEquals("HIGH", record.getSeverity());
    }
}

