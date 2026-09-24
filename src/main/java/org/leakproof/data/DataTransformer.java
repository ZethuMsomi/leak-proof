package org.leakproof.data;
import org.leakproof.*;

import java.util.ArrayList;
import java.util.List;

public class DataTransformer {

   private List<ScanResult> results;
   private List<FindingRecord> records;

   public DataTransformer(List<ScanResult> results){
       this.results = results;
       this.records = new ArrayList<>();
   }

   public List<FindingRecord> transform(){
       for(ScanResult result : results){
           String severity = "HIGH";

           if(result.getLeakName().equals("Private Key")){
               severity = "CRITICAL";
           }
           FindingRecord record = new FindingRecord(
                   result.getFile(),
                   result.getLineNumber(),
                   result.getLeakName(),
                   severity
           );

           records.add(record);
       }
       return records;
   }
}
