package org.leakproof.data;

import java.io.File;

public class FindingRecord {

    private File file;
    private int lineNumber;
    private String leakName;
    private String severity;

    public FindingRecord(File file, int lineNumber, String leakName, String severity){
        this.file = file;
        this.lineNumber = lineNumber;
        this.leakName = leakName;
        this.severity = severity;
    }

    public File getFile(){
        return this.file;
    }

    public int getLineNumber(){
        return this.lineNumber;
    }

    public String getLeakName(){
        return this.leakName;
    }

    public String getSeverity(){
        return this.severity;
    }



}
