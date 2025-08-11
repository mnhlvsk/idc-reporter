package com.idc.reporter.export;

import java.io.IOException;

import com.idc.reporter.model.TableView;

/**
 * Common interface for exporting a {@link TableView} to a file.
 */
public interface Exporter {

    void export(TableView table, String filePath) throws IOException;
}
