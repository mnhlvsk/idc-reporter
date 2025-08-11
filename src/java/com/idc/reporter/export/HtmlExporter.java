package com.idc.reporter.export;

import java.io.PrintWriter;

import com.idc.reporter.model.TableRow;
import com.idc.reporter.model.TableView;

/**
 * Exports a {@link TableView} to a HTML file.
 * Output is a simple HTML table with the title, country, timescale,
 * and rows with vendor, units, and share percentage.
 */
public class HtmlExporter implements Exporter {

    @Override
    public void export(TableView table, String filePath) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println("<html><body>");
            writer.printf("<h3>%s, %s, %s</h3>%n", table.title(), table.country(), table.timescale());
            writer.println("<table border='1'><tr><th>Vendor</th><th>Units</th><th>Share</th></tr>");

            for (TableRow row : table.rows()) {
                writer.printf("<tr><td>%s</td><td>%,.0f</td><td>%.1f%%</td></tr>%n",
                         row.vendor(), row.units(), row.share());
            } 

            writer.printf("<tr><td>Total</td><td>%.0f</td><td>100%%</td></tr>%n", table.totalUnits());
            writer.println("</table></body></html>");
        } catch (Exception e) {
            throw new RuntimeException("Error exporting HTML", e);
        }
    }
}
