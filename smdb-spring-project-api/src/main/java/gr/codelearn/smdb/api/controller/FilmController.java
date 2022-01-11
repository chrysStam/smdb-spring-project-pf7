package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.service.BaseService;
import gr.codelearn.smdb.api.service.ContentService;
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
@RequestMapping("/contents/films")
public class FilmController extends ContentController<Film> {
	private final FilmService filmService;

	@Override
	protected ContentService<Film> getBaseService() {
		return filmService;
	}

	@GetMapping(params = {"genre"})
	public ResponseEntity<ApiResponse<List<Content>>> find2(@RequestParam("genre") Set<Genre> genre) {
		return ResponseEntity.ok(
				ApiResponse.<List<Content>>builder().data(filmService.findAllByGenresIn(genre)).build());
	}
}
