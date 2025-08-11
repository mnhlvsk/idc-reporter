package com.idc.reporter.export;

import com.idc.reporter.model.TableView;

/**
 * Stub for future CSV exporting.
 */
public class CsvExporter implements Exporter {

    @Override
    public void export(TableView table, String filePath) {
        // todo implement using OpenCSV CSVWriter
        throw new UnsupportedOperationException("Unimplemented method 'export'");
    }

}
