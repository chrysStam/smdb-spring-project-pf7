package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.helpers.ContributorGenre;
import gr.codelearn.smdb.api.helpers.YearGenresStat;
import gr.codelearn.smdb.api.domain.Role;
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


	@GetMapping(path = "contents/top", params = {"num"})
	public ResponseEntity<ApiResponse<List<Content>>> getTopXHighIMDBScore(@RequestParam("num") Integer num){
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(reportService.getTopXHighIMDBScore(num))
											.build());
	}


	@GetMapping(path = "contents/person", params = {"name","surname"})
	public ResponseEntity<ApiResponse<List<Content>>> getAllContentByContributorByFullName(@RequestParam("name") String name,
																		@RequestParam("surname") String surname) {
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(reportService.getAllContentByContributorByFullName(name,surname))
											.build());
	}

	@GetMapping(path = "contents/person/{pId}", headers = "action=getContributionsOfPersonById", produces =
			"application/vnd.app-v1+json")
	public ResponseEntity<ApiResponse<List<Content>>> getContributionsOfPersonById(
			@PathVariable(value = "pId") Long personId) {
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
										 .data(reportService.getAllContentByContributorById(personId)).build());
	}

	@GetMapping(path = "contents/person", params = {"name","surname","role"})
	public ResponseEntity<ApiResponse<List<Content>>> getByContributorByNameAndRole(@RequestParam("name") String name,
																			   @RequestParam("surname") String surname,
																			   @RequestParam("role") Role role) {
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(reportService.getAllContentByContributorByFullNameAndRole(name,surname,role))
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

	@GetMapping(path = "years")
	public ResponseEntity<ApiResponse<List<YearGenresStat>>> getNoOfContentPerYearPerGenre() {
		return ResponseEntity.ok(ApiResponse.<List<YearGenresStat>>builder()
										 .data(reportService.getNoOfContentPerYearPerGenre())
										 .build());
	}

	@GetMapping(path = "/person/{pId}/contents/genre",
				headers = "action=getAllContentOfContributorByIdPerGenres",
				produces ="application/vnd.app-v1+json")
	public ResponseEntity<ApiResponse<List<ContributorGenre>>> getAllContentOfContributorByIdPerGenres(@PathVariable(value = "pId") Long personId) {
		return ResponseEntity.ok(ApiResponse.<List<ContributorGenre>>builder()
										 .data(reportService.getAllContentOfContributorByIdPerGenres(personId)).build());
	}

	@GetMapping(path = "/person/{pId}/contents", params = "role", headers = "action" +
			"=getContributionsOfPersonByIdByRole",
			produces =
					"application/vnd.app-v1+json")
	public ResponseEntity<ApiResponse<List<Content>>> getContributionsOfPersonByIdByRole(
			@PathVariable(value = "pId") Long personId,
			@RequestParam("role") Role role) {
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
										 .data(reportService.getAllContentByContributorByIdAndRole(personId, role)).build());
	}

}
