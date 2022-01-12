package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.domain.TVShow;
import gr.codelearn.smdb.api.service.ContentService;
import gr.codelearn.smdb.api.service.TVShowService;
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
@RequestMapping("/contents/tvshows")
public class TVShowController extends ContentController<TVShow> {
	private final TVShowService tvShowService;

	@Override
	protected ContentService<TVShow> getBaseService() {
		return tvShowService;
	}

	@GetMapping(params = {"completed"})
	public Callable<ResponseEntity<ApiResponse<List<TVShow>>>> findByCompletion(
			@RequestParam("completed") Boolean completed) {
		return () -> ResponseEntity.ok(
				ApiResponse.<List<TVShow>>builder().data(tvShowService.findByCompletion(completed)).build());
	}
}
