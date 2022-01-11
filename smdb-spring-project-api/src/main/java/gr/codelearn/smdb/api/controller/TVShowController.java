package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.domain.TVShow;
import gr.codelearn.smdb.api.service.BaseService;
import gr.codelearn.smdb.api.service.ContentService;
import gr.codelearn.smdb.api.service.FilmService;
import gr.codelearn.smdb.api.service.TVShowService;
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
@RequestMapping("/contents/tvshows")
public class TVShowController extends ContentController<TVShow> {
	private final TVShowService tvShowService;

	@Override
	protected ContentService<TVShow> getBaseService() {
		return tvShowService;
	}

	@GetMapping(path= "search", params = {"title"})
	public ResponseEntity<ApiResponse<List<TVShow>>> searchByTitle(@RequestParam("title") String title) {
		return ResponseEntity.ok(ApiResponse.<List<TVShow>>builder()
											.data(tvShowService.searchByTitle(title))
											.build());
	}

	@GetMapping(path = "top", params = {"num"})
	public ResponseEntity<ApiResponse<List<TVShow>>> findTopRatings(@RequestParam("num") Integer num){
		return ResponseEntity.ok(ApiResponse.<List<TVShow>>builder()
											.data(tvShowService.findTopRatings(num))
											.build());
	}



	@GetMapping(params = {"name","surname"})
	public ResponseEntity<ApiResponse<List<TVShow>>> findByContributor(@RequestParam("name") String name,
																	 @RequestParam("surname") String surname) {
		return ResponseEntity.ok(ApiResponse.<List<TVShow>>builder()
											.data(tvShowService.findByContributorByFullName(name,surname))
											.build());
	}

	@GetMapping(params = {"name","surname","role"})
	public ResponseEntity<ApiResponse<List<TVShow>>> findByContributorAndRoleFullName(@RequestParam("name") String name,
																			@RequestParam("surname") String surname,
																			@RequestParam("role") Role role) {
		return ResponseEntity.ok(ApiResponse.<List<TVShow>>builder()
											.data(tvShowService.findByContributorAndRoleFullName(name,surname,role))
											.build());
	}



	@GetMapping(params = {"genre"})
	public ResponseEntity<ApiResponse<List<TVShow>>> find2(@RequestParam("genre") Set<Genre> genre) {
		return ResponseEntity.ok(ApiResponse.<List<TVShow>>builder().data(tvShowService.findAllByGenresIn(genre)).build());
	}
}
