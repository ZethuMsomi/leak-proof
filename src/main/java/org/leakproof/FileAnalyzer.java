package org.leakproof;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class FileAnalyzer {

    private static final Map<String, Pattern> securityLeaks = new HashMap<>();

    static {
        // Leak 1: AWS Access Key ID (starts with AKIA followed by 16 characters)
        securityLeaks.put("AWS Keys:", Pattern.compile("AKIA "));

        // Leak 2: Asymmetric Private Keys (Catches RSA, EC, DSA, or OPENSSH keys)
        securityLeaks.put("Private Key", Pattern.compile("-----BEGIN (RSA|EC|DSA|OPENSSH) PRIVATE KEY-----"));

        // You can add as many rules here as you want later!
    }

    public List<ScanResult> scanFile(File file) {
        List<ScanResult> results = new ArrayList<>();
        int lineNumber = 1;
        return results;
    }


}