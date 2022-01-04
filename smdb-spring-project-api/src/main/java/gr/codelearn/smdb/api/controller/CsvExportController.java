package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.service.CsvExportService;
import gr.codelearn.smdb.api.transfer.ApiResponse;
import gr.codelearn.smdb.api.transfer.KeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exports")
public class CsvExportController {
	private final CsvExportService csvExportService;

	@RequestMapping(value = "/all", produces = "text/csv")
	public ResponseEntity<ApiResponse<List<KeyValue<String, Integer>>>> exportAll(HttpServletResponse servletResponse) {
		try {
			servletResponse.addHeader("Content-Disposition", "attachment; filename=\"all.csv\"");
			return ResponseEntity.ok(ApiResponse.<List<KeyValue<String, Integer>>>builder()
												.data(csvExportService.exportAll(servletResponse.getWriter())).build());
		} catch (IOException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to export data", ex);
		}
	}

	@RequestMapping(value = "/people", produces = "text/csv")
	public ResponseEntity<ApiResponse<KeyValue<String, Integer>>> exportPeople(HttpServletResponse servletResponse) {
		try {
			servletResponse.addHeader("Content-Disposition", "attachment; filename=\"people.csv\"");
			return ResponseEntity.ok(ApiResponse.<KeyValue<String, Integer>>builder()
												.data(csvExportService.exportPeople(servletResponse.getWriter()))
												.build());
		} catch (IOException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to export data", ex);
		}
	}
}
