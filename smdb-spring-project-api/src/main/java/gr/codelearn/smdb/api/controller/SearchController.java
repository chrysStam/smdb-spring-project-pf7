package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.TVShow;
import gr.codelearn.smdb.api.service.BaseService;
import gr.codelearn.smdb.api.service.PersonService;
import gr.codelearn.smdb.api.service.SearchService;
import gr.codelearn.smdb.api.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
	private final SearchService searchService;

	@GetMapping(path = "/films",params = {"keyword"})
	public ResponseEntity<ApiResponse<List<Content>>> searchContentByTitleOrPlotSummary(
			@RequestParam(value = "keyword") String keyword) {
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(searchService.findByTitleContainingIgnoreCaseOrPlotSummaryContainingIgnoreCase(keyword, keyword)).build());
	}

	@GetMapping(path = "/persons",params = {"keyword"})
	public ResponseEntity<ApiResponse<List<Person>>> searchPersonByNameOrSurname(
			@RequestParam(value = "keyword") String keyword) {
		return ResponseEntity.ok(ApiResponse.<List<Person>>builder()
											.data(searchService.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(keyword, keyword)).build());
	}


}
