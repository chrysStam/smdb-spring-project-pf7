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
import java.util.concurrent.Callable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {
	private final ReportService reportService;

//	 Report 1: Return the top X high-rated content.
	@GetMapping(params = {"top"})
	public Callable<ResponseEntity<ApiResponse<List<Content>>>> getTopXHighIMDBScore(@RequestParam("top") Integer num){
		return ()-> ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(reportService.getTopXHighIMDBScore(num))
											.build());
	}

//	Report 2: Return all content associated with a given individual regardless of his/her contributing role.
//	BY FULL NAME
	@GetMapping(params = {"name","surname"},  headers = "action=getAllContentByContributorByFullName")
	public Callable<ResponseEntity<ApiResponse<List<Content>>>> getAllContentByContributorByFullName(@RequestParam("name") String name,
																		@RequestParam("surname") String surname) {
		return ()-> ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(reportService.getAllContentByContributorByFullName(name,surname))
											.build());
	}

// 	BY ID
	@GetMapping(params = {"pId"}, headers = "action=getAllContentByContributorById")
	public Callable<ResponseEntity<ApiResponse<List<Content>>>> getAllContentByContributorById(@RequestParam("pId") Long personId) {
		return ()-> ResponseEntity.ok(ApiResponse.<List<Content>>builder()
										 .data(reportService.getAllContentByContributorById(personId)).build());
	}

//	Report 3: Return all content associated with a given individual for a given contributing role.
//	BY FULL NAME
	@GetMapping(params = {"name","surname","role"}, headers = "action=getAllContentByContributorByFullNameAndRole")
	public Callable<ResponseEntity<ApiResponse<List<Content>>>> getAllContentByContributorByFullNameAndRole(
			@RequestParam("name") String name,
		   @RequestParam("surname") String surname,
		   @RequestParam("role") Role role) {
		return ()-> ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(reportService.getAllContentByContributorByFullNameAndRole(name,surname,role))
											.build());
	}

// BY ID
	@GetMapping(params = {"pId","role"}, headers = "action=getAllContentByContributorByIdAndRole")
	public Callable<ResponseEntity<ApiResponse<List<Content>>>> getAllContentByContributorByIdAndRole(
			@RequestParam("pId")  Long personId,
			@RequestParam("role") Role role) {
		return ()-> ResponseEntity.ok(ApiResponse.<List<Content>>builder()
										 .data(reportService.getAllContentByContributorByIdAndRole(personId, role)).build());
	}

//	Report 4: Return all content for a given genre
	@GetMapping(params = {"genre"}, headers = "action=getAllContentByGenre" )
	public Callable<ResponseEntity<ApiResponse<List<Content>>>> getAllContentByGenre(@RequestParam("genre") Genre genre) {
		return ()-> ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(reportService.getAllContentByGenre(genre))
											.build());
	}

//	Report 5: Return the number of shows per genre
	@GetMapping(headers = "action=getNoOfContentPerGenre")
	public Callable<ResponseEntity<ApiResponse<List<NoOfContentPerGenreDto>>>> getNoOfContentPerGenre() {
		return ()-> ResponseEntity.ok(ApiResponse.<List<NoOfContentPerGenreDto>>builder()
											.data(reportService.getNoOfContentPerGenre())
											.build());
	}

//	Report 6: Return the numbers of shows per year per genre
	@GetMapping(headers = "action=getNoOfContentPerYearPerGenre")
	public Callable<ResponseEntity<ApiResponse<List<YearGenresStat>>>> getNoOfContentPerYearPerGenre() {
		return ()-> ResponseEntity.ok(ApiResponse.<List<YearGenresStat>>builder()
										 .data(reportService.getNoOfContentPerYearPerGenre())
										 .build());
	}

	//	Report 7: Return all content associated with a given individual organized per genre
	@GetMapping(params = {"pId"}, headers = "action=getAllContentOfContributorByIdPerGenres")
	public Callable<ResponseEntity<ApiResponse<List<ContributorGenre>>>> getAllContentOfContributorByIdPerGenres(@RequestParam(value = "pId") Long personId) {
		return ()-> ResponseEntity.ok(ApiResponse.<List<ContributorGenre>>builder()
										 .data(reportService.getAllContentOfContributorByIdPerGenres(personId)).build());
	}


}
