package org.leakproof.data;

import org.junit.jupiter.api.Test;
import org.leakproof.ScanResult;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataTransformerTest {

    @Test
    void transformsAwsKeyToHighSeverity() {
        ScanResult result = new ScanResult(
                new File("test.java"),
                10,
                "AWS Key"
        );

        DataTransformer transformer =
                new DataTransformer(List.of(result));

        List<FindingRecord> records = transformer.transform();

        assertEquals(1, records.size());
        assertEquals("AWS Key", records.get(0).getLeakName());
        assertEquals("HIGH", records.get(0).getSeverity());
    }

    @Test
    void transformsPrivateKeyToCriticalSeverity() {
        ScanResult result = new ScanResult(
                new File("private.key"),
                5,
                "Private Key"
        );

        DataTransformer transformer =
                new DataTransformer(List.of(result));

        List<FindingRecord> records = transformer.transform();

        assertEquals(1, records.size());
        assertEquals("Private Key", records.get(0).getLeakName());
        assertEquals("CRITICAL", records.get(0).getSeverity());
    }
}
