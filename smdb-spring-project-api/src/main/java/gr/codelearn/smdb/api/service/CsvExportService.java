package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.transfer.KeyValue;

import java.io.Writer;
import java.util.List;

public interface CsvExportService {

	List<KeyValue<String, String>> exportList();

	void export(Writer writer, String table);
}
