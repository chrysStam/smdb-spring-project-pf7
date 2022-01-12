package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.TVShow;
import gr.codelearn.smdb.api.helpers.MultiSearch;
import gr.codelearn.smdb.api.helpers.MultiSearch2;
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

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
	private final SearchService searchService;

	@GetMapping(path = "/multi",params = {"keyword"})
	public ResponseEntity<ApiResponse<List<MultiSearch>>> multiSearch(
			@RequestParam(value = "keyword") String keyword) {
		List<MultiSearch> results = searchService.multiSearch(keyword);
		return ResponseEntity.ok(ApiResponse.<List<MultiSearch>>builder()
											.data(results).totalResults(results.size()).build());
	}

	@GetMapping(path = "/multi2",params = {"keyword"})
	public ResponseEntity<ApiResponse<List<MultiSearch2>>> multiSearch2(
			@RequestParam(value = "keyword") String keyword) {
		List<MultiSearch2> results = searchService.multiSearch2(keyword);
		return ResponseEntity.ok(ApiResponse.<List<MultiSearch2>>builder()
											.data(results).totalResults(results.size()).build());
	}


}
