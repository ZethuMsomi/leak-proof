package org.leakproof;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Scans a target directory and its files for potential security leaks.
 */

public class ProjectScanner {

    private FileAnalyzer fileAnalyzer;
    private List<ScanResult> allResults;

    public ProjectScanner() {
        this.fileAnalyzer = new FileAnalyzer();
        this.allResults = new ArrayList<>();
    }

    public List<ScanResult> startScan(String targetPath) {
        File filePath = new File(targetPath);
        List<ScanResult> results = new ArrayList<>();

        if (!filePath.exists()) {
            System.err.println("File does not exist");
            return allResults;
        }

        if (!filePath.isDirectory()) {
            System.err.println("File is not a directory");
            return allResults;
        }
        traverseDirectory(filePath);
        return allResults;

    }

    public void traverseDirectory(File directory){
        File[] allFiles = directory.listFiles();
        if(allFiles == null){
            return;
        }
        for(File file : allFiles){
            if(file.isDirectory()){
                traverseDirectory(file);
            } else if (file.isFile()) {
                List<ScanResult> fileResults = fileAnalyzer.scanFile(file);
                allResults.addAll(fileResults);

            }
        }
    }

}
