package gr.codelearn.smdb.api.service;

import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.util.zip.ZipOutputStream;

public interface CsvExportService {

	void exportAll(ZipOutputStream zipOutputStream) throws IOException;

	void export(CSVPrinter csvPrinter, String table) throws IOException;
}
