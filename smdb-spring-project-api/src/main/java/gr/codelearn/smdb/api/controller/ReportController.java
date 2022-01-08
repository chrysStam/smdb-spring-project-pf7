package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.service.BaseService;
import gr.codelearn.smdb.api.service.ReportService;
import gr.codelearn.smdb.api.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {
	private final ReportService reportService;



	@GetMapping(path= "search", params = {"title"})
	public ResponseEntity<ApiResponse<List<Content>>> searchByTitle(@RequestParam("title") String title) {
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(reportService.searchByTitle(title))
											.build());
	}


	@GetMapping(path = "top", params = {"num"})
	public ResponseEntity<ApiResponse<List<Content>>> findTopRatings(@RequestParam("num") Integer num){
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(reportService.findTopRatings(num))
											.build());
	}




	@GetMapping(params = {"name","surname"})
	public ResponseEntity<ApiResponse<List<Content>>> findByContributor(@RequestParam("name") String name,
																		@RequestParam("surname") String surname) {
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(reportService.findByContributor(name,surname))
											.build());
	}

	@GetMapping(params = {"name","surname","role"})
	public ResponseEntity<ApiResponse<List<Content>>> findByContributorAndRole(@RequestParam("name") String name,
																			   @RequestParam("surname") String surname,
																			   @RequestParam("role") Role role) {
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(reportService.findByContributorAndRole(name,surname,role))
											.build());
	}


	@GetMapping(params = {"genre"})
	public ResponseEntity<ApiResponse<List<Content>>> find2(@RequestParam("genre") Set<Genre> genre) {
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(reportService.findAllByGenresIn(genre))
											.build());
	}


	@GetMapping(path = "native")
	public ResponseEntity<ApiResponse<Integer>> findNative(){
		return ResponseEntity.ok(ApiResponse.<Integer>builder()
											.data(reportService.findNative())
											.build());
	}
}
