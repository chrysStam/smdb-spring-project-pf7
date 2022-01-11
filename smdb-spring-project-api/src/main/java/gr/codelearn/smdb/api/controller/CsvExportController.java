package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.service.CsvExportService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipOutputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exports")
public class CsvExportController {
	private final CsvExportService csvExportService;

	// Exports all tables inside a zip file
	@RequestMapping(produces = "application/zip")
	public void exportAll(HttpServletResponse servletResponse) throws IOException {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String currentDateTime = dateFormatter.format(new Date());

		servletResponse.setStatus(HttpServletResponse.SC_CREATED);
		servletResponse.addHeader("Content-Disposition", "attachment; filename=\"smdb_ " + currentDateTime + ".zip\"");

		ZipOutputStream zipOutputStream = new ZipOutputStream(servletResponse.getOutputStream());
		csvExportService.exportAll(zipOutputStream);

		zipOutputStream.close();
	}

	// Export specific table
	@RequestMapping(value = "/{table}", produces = "text/csv")
	public void export(@PathVariable final String table, HttpServletResponse servletResponse) throws IOException {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String currentDateTime = dateFormatter.format(new Date());

		servletResponse.setStatus(HttpServletResponse.SC_CREATED);
		servletResponse.addHeader("Content-Disposition",
								  "attachment; filename=\"" + table + "_" + currentDateTime + ".csv\"");

		CSVPrinter csvPrinter = new CSVPrinter(servletResponse.getWriter(), CSVFormat.DEFAULT);
		csvExportService.export(csvPrinter, table);

		csvPrinter.flush();
		csvPrinter.close();
	}
}
