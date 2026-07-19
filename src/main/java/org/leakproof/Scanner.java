package org.leakproof;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Scanner {

    private FileAnalyzer fileAnalyzer;
    private List<ScanResult> allResults;

    public Scanner(FileAnalyzer fileAnalyzer) {
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
        // TODO: Step 3 - Get array of files using directory.listFiles()
        File[] allFiles = directory.listFiles();
        // TODO: Step 3 - If the array is null, return early
        if(allFiles == null){
            return;
        }
        // TODO: Step 4 - Loop through the array
        for(File file : allFiles){
            if(file.isDirectory()){
                traverseDirectory(file);
            }

            if(file.isFile()){

            }
        }
        // TODO: Step 4 - If it's a directory, call traverseDirectory again
        // TODO: Step 4 - If it's a file, pass it to fileAnalyzer.scanFile() and addAll() to allResults
    }

}
