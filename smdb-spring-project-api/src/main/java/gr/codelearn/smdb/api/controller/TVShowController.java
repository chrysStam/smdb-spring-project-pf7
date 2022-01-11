package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.domain.TVShow;
import gr.codelearn.smdb.api.service.ContentService;
import gr.codelearn.smdb.api.service.TVShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contents/tvshows")
public class TVShowController extends ContentController<TVShow> {
	private final TVShowService tvShowService;

	@Override
	protected ContentService<TVShow> getBaseService() {
		return tvShowService;
	}
}
