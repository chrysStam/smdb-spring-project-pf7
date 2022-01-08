package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.service.BaseService;
import gr.codelearn.smdb.api.service.FilmService;
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
@RequestMapping("/films")
public class FilmController extends AbstractController<Film> {
	private final FilmService filmService;

	@Override
	protected BaseService<Film, Long> getBaseService() {
		return filmService;
	}

	@GetMapping(params = {"t"})
	public Film find(@RequestParam("t") String title) {
		return filmService.findByTitle(title);
	}

	@GetMapping(path= "search", params = {"title"})
	public ResponseEntity<ApiResponse<List<Content>>> searchByTitle(@RequestParam("title") String title) {
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(filmService.searchByTitle(title))
											.build());
	}


	@GetMapping(path = "top", params = {"num"})
	public ResponseEntity<ApiResponse<List<Content>>> findTopRatings(@RequestParam("num") Integer num){
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(filmService.findTopRatings(num))
											.build());
	}




	@GetMapping(params = {"name","surname"})
	public ResponseEntity<ApiResponse<List<Content>>> findByContributor(@RequestParam("name") String name,
																	   @RequestParam("surname") String surname) {
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(filmService.findByContributor(name,surname))
											.build());
	}

	@GetMapping(params = {"name","surname","role"})
	public ResponseEntity<ApiResponse<List<Content>>> findByContributorAndRole(@RequestParam("name") String name,
																	 @RequestParam("surname") String surname,
																			@RequestParam("role") Role role) {
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(filmService.findByContributorAndRole(name,surname,role))
											.build());
	}


	@GetMapping(params = {"genre"})
	public ResponseEntity<ApiResponse<List<Content>>> find2(@RequestParam("genre") Set<Genre> genre) {
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(filmService.findAllByGenresIn(genre))
											.build());
	}

}
