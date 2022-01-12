package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.helpers.MultiSearch;
import gr.codelearn.smdb.api.service.SearchService;
import gr.codelearn.smdb.api.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
	private final SearchService searchService;

	@GetMapping(path = "/multi",params = {"keyword"})
	public ResponseEntity<ApiResponse<List<MultiSearch<?>>>> multiSearch(
			@RequestParam(value = "keyword") String keyword,
			@RequestParam(value = "grouped", defaultValue = "false") Boolean grouped) {
		return ResponseEntity.ok(ApiResponse.<List<MultiSearch<?>>>builder()
										 .data(searchService.multiSearch(keyword, grouped)).build());
	}

}
