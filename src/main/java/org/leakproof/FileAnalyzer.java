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
        securityLeaks.put("AWS Key", Pattern.compile("AKIA[0-9A-Z]{16}"));

        // Leak 2: Asymmetric Private Keys (Catches RSA, EC, DSA, or OPENSSH keys)
        securityLeaks.put("Private Key", Pattern.compile("-----BEGIN (RSA|EC|DSA|OPENSSH) PRIVATE KEY-----"));

        // Leak 3: AWS Secrets Manager ARN (starts with arn:aws:secretsmanager: followed by region, a 12-digit ID, and the secret name)
        securityLeaks.put("AWS Secrets Manager ARN", Pattern.compile("arn:aws:secretsmanager:[a-z0-9-]+:\\d{12}:secret:[a-zA-Z0-9/_+=.@-]+"));

        // Leak 4: Slack Bot Token (starts with xoxb- followed by two blocks of 10-13 digits and 24 alphanumeric characters)
        securityLeaks.put("Slack Token", Pattern.compile("xoxb-[0-9]{10,13}-[0-9]{10,13}-[a-zA-Z0-9]{24}"));

        // Leak 5: Google Cloud API Key (starts with AIza followed by 35 alphanumeric characters, dashes, or underscores)
        securityLeaks.put("GCP API Key", Pattern.compile("AIza[0-9A-Za-z\\-_]{35}"));

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