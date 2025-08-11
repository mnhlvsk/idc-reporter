package com.idc.reporter;

import com.idc.reporter.export.HtmlExporter;
import com.idc.reporter.service.CsvLoader;
import com.idc.reporter.service.TableService;

public class Main {
	public static void main(String[] args) {

		var records = CsvLoader.load("data/data.csv");
		var table = TableService.createTableView(records, "Table 1, PC Quarterly Market Share", "Czech Republic",
				"2010 Q3");

		new HtmlExporter().export(table, "exports/raw.html");

		var sortedByVendor = table.sortedByVendor();
		new HtmlExporter().export(sortedByVendor, "exports/sortedByVendor.html");

		var sortedByUnits = table.sortedByUnits();
		new HtmlExporter().export(sortedByUnits, "exports/sortedByUnits.html");

		var rowIndex = sortedByUnits.rowIndexOf("Apple");
		System.out.println("Apple row index in table sorted by units: " + rowIndex.getAsInt());

		var vendor = sortedByUnits.findRowByVendor("Dell");
		System.out.printf("Dell units: %,.0f share: %.1f%%%n", vendor.get().units(), vendor.get().share());
	}
}
