package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.service.BaseService;
import gr.codelearn.smdb.api.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/films")
public class FilmController extends AbstractController<Film> {
	private final FilmService filmService;

	@Override
	protected BaseService<Film, Long> getBaseService() {
		return filmService;
	}
}
