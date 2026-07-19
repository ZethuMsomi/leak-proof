package org.leakproof;

import java.util.List;

public class ReportGenerator {

    public void generateReport(List<ScanResult> results){

        if(results.isEmpty()){
            System.out.println("Scan complete. No leaks found. Great Job!");
            return;
        }

        System.out.println("CRITICAL: LEAKS FOUND");
        System.out.println("Number of leaks found: " + results.size());
        System.out.println("-----------------------------------------------------");

        for(ScanResult result:results){
            System.out.println("Leak Type : " + result.getLeakName());
            System.out.println("Location : " + result.getFile().getAbsolutePath());
            System.out.println("Line # :" + result.getLineNumber());
            System.out.println("----------------------------------------------------");
        }
    }
}

