package com.idc.reporter.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public record TableView(String title, String country, String timescale, List<TableRow> rows, double totalUnits) {

    public TableView sortedByVendor() {
        var sorted = new ArrayList<>(rows);
        sorted.sort(Comparator.comparing(TableRow::vendor, String.CASE_INSENSITIVE_ORDER));
        return new TableView(title, country, timescale, sorted, totalUnits);
    }

    public TableView sortedByUnits() {
        var sorted = new ArrayList<>(rows);
        sorted.sort((r1, r2) -> Double.compare(r2.units(), r1.units()));
        return new TableView(title, country, timescale, sorted, totalUnits);
    }

    public OptionalInt rowIndexOf(String vendor) {
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).vendor().equalsIgnoreCase(vendor))
                return OptionalInt.of(i + 1);
        }
        return OptionalInt.empty();
    }

    public Optional<TableRow> findRowByVendor(String vendor) {
        return rows.stream()
                .filter(r -> r.vendor().equalsIgnoreCase(vendor))
                .findFirst();
    }
}
