package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.service.BaseService;
import gr.codelearn.smdb.api.service.ReportService;
import gr.codelearn.smdb.api.transfer.ApiResponse;
import gr.codelearn.smdb.api.transfer.NoOfContentPerGenreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<ApiResponse<List<Content>>> getTopXHighIMDBScore(@RequestParam("num") Integer num){
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(reportService.getTopXHighIMDBScore(num))
											.build());
	}


	@GetMapping(params = {"name","surname"})
	public ResponseEntity<ApiResponse<List<Content>>> getByContributorByName(@RequestParam("name") String name,
																		@RequestParam("surname") String surname) {
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(reportService.getByContributorByName(name,surname))
											.build());
	}

	@GetMapping(params = {"name","surname","role"})
	public ResponseEntity<ApiResponse<List<Content>>> getByContributorByNameAndRole(@RequestParam("name") String name,
																			   @RequestParam("surname") String surname,
																			   @RequestParam("role") Role role) {
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(reportService.getByContributorByNameAndRole(name,surname,role))
											.build());
	}


	@GetMapping(path = "contents", params = {"genre"})
	public ResponseEntity<ApiResponse<List<Content>>> find2(@RequestParam("genre") Genre genre) {
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(reportService.getAllContentByGenre(genre))
											.build());
	}

	@GetMapping(path = "genres/num")
	public ResponseEntity<ApiResponse<List<NoOfContentPerGenreDto>>> getNoOfContentPerGenre() {
		return ResponseEntity.ok(ApiResponse.<List<NoOfContentPerGenreDto>>builder()
											.data(reportService.getNoOfContentPerGenre())
											.build());
	}
}
