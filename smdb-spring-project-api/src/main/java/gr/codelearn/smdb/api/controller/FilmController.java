package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.domain.Film;
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
import java.util.concurrent.Callable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contents/films")
public class FilmController extends ContentController<Film> {
	private final FilmService filmService;

	@Override
	protected ContentService<Film> getBaseService() {
		return filmService;
	}

	@GetMapping(params = {"topBoxOffice"})
	public Callable<ResponseEntity<ApiResponse<List<Film>>>> findTopXByBoxOffice(
			@RequestParam("topBoxOffice") Integer num) {
		return () -> ResponseEntity.ok(
				ApiResponse.<List<Film>>builder().data(filmService.findTopXOrderedByBoxOffice(num)).build());
	}
}
