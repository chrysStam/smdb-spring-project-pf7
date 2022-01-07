package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.service.CsvExportService;
import gr.codelearn.smdb.api.transfer.ApiResponse;
import gr.codelearn.smdb.api.transfer.KeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

	@RequestMapping("/list")
	public ResponseEntity<ApiResponse<List<KeyValue<String, String>>>> exportList() {
		return ResponseEntity.ok(ApiResponse.<List<KeyValue<String, String>>>builder()
												.data(csvExportService.exportList()).build());
	}

	@RequestMapping(value = "/{table}", produces = "text/csv")
	public void export(@PathVariable final String table, HttpServletResponse servletResponse) {
		try {
			servletResponse.setStatus(HttpServletResponse.SC_CREATED);
			servletResponse.addHeader("Content-Disposition", "attachment; filename=\"" + table + ".csv\"");
			csvExportService.export(servletResponse.getWriter(), table);
		} catch (IOException ex) {
			//servletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);	// Doesn't work as needed
		} catch (IllegalArgumentException ex) {
			//servletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST);	// Doesn't work as needed
		}
	}
}
