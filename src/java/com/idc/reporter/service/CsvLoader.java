package com.idc.reporter.service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.idc.reporter.model.Record;
import com.opencsv.CSVReader;

public class CsvLoader {

    public static List<Record> load(String filePath) {
        List<Record> records = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext(); // skip header
            while ((line = reader.readNext()) != null) {
                records.add(new Record(line[0], line[1], line[2], Double.parseDouble(line[3])));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading CSV", e);
        }
        return records;
    }
}
