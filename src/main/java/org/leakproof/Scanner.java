package org.leakproof;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Scanner {

    private FileAnalyzer fileAnalyzer;
    private List<ScanResult> allResults;

    public List<ScanResult> startScan(String targetPath){
        File filePath = new File(targetPath);
        List<ScanResult> results = new ArrayList<>();

        if(filePath.exists() && !filePath.isDirectory()) {
            System.err.println("File is not a directory");
            return results;
        }
        return results;
    }

}
