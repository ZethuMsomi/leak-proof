package org.leakproof;

import java.io.File;

/**
 * Stores information about a detected security leak, including the file,
 * line number, and type of leak.
 */
public class ScanResult {

    private File file;
    private int lineNumber;
    private String leakName;

    public ScanResult(File file, int lineNumber, String leakName) {
        this.file = file;
        this.lineNumber = lineNumber;
        this.leakName = leakName;
    }

    public File getFile(){
        return file;
    }

    public int getLineNumber(){
        return lineNumber;
    }

    public String getLeakName(){
        return leakName;
    }
}
