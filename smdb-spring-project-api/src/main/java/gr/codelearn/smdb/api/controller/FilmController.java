package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.service.ContentService;
import gr.codelearn.smdb.api.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contents/films")
public class FilmController extends ContentController<Film> {
	private final FilmService filmService;

	@Override
	protected ContentService<Film> getBaseService() {
		return filmService;
	}
}
