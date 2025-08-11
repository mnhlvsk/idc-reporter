package com.idc.reporter.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.idc.reporter.model.Record;
import com.idc.reporter.model.TableRow;
import com.idc.reporter.model.TableView;

public class TableService {

    public static TableView createTableView(List<Record> records, String title, String country, String timescale) {

        // slice by country and timescale
        var filtered = records.stream()
                .filter(r -> r.country().equalsIgnoreCase(country))
                .filter(r -> r.timescale().equalsIgnoreCase(timescale))
                .toList();

        double totalUnits = filtered.stream().mapToDouble(Record::units).sum();
        if (totalUnits == 0.0)
            return new TableView(title, country, timescale, List.of(), 0);

        // group by vendor
        Map<String, Double> byVendor = filtered.stream()
                .collect(Collectors.groupingBy(
                        Record::vendor, Collectors.summingDouble(Record::units)));

        // map to rows with share
        List<TableRow> rows = byVendor.entrySet().stream()
                .map(e -> {
                    double units = e.getValue();
                    double share = totalUnits == 0 ? 0 : (units / totalUnits) * 100.0;
                    return new TableRow(e.getKey(), units, share);
                })
                .sorted(Comparator.comparingDouble(TableRow::units).reversed())
                .toList();
        return new TableView(title, country, timescale, rows, totalUnits);
    }
}
