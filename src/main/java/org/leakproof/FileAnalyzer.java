package org.leakproof;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileAnalyzer {

    private static final Map<String, Pattern> securityLeaks = new HashMap<>();

    static {
        // Leak 1: AWS Access Key ID (starts with AKIA followed by 16 characters)
        securityLeaks.put("AWS Keys:", Pattern.compile("AKIA[0-9A-Z]{16}"));

        // Leak 2: Asymmetric Private Keys (Catches RSA, EC, DSA, or OPENSSH keys)
        securityLeaks.put("Private Key", Pattern.compile("-----BEGIN (RSA|EC|DSA|OPENSSH) PRIVATE KEY-----"));

        // You can add as many rules here as you want later!
    }

    public List<ScanResult> scanFile(File file){
        List<ScanResult> results = new ArrayList<>();
        int lineNumber = 1;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null){
                for(Map.Entry<String, Pattern> entry : securityLeaks.entrySet()){
                    String entryName = entry.getKey();
                    Pattern pattern = entry.getValue();

                    Matcher matcher = pattern.matcher(line);
                    if(matcher.find()){
                        ScanResult leak = new ScanResult(file, lineNumber, entryName);
                        results.add(leak);
                    }

                }
                lineNumber++;
            }
        } catch (IOException e) {
            System.err.println("Could not find file: " + file.getAbsolutePath());
        }
        return results;
    }
}