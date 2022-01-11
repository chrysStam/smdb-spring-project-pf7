package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.service.ContentService;
import gr.codelearn.smdb.api.transfer.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/contents")
public abstract class ContentController<T extends Content> extends AbstractController<T> {

	@Override
	protected abstract ContentService<T> getBaseService();

	@GetMapping(params = {"title"})
	public Callable<ResponseEntity<ApiResponse<List<T>>>> findByTitle(@RequestParam("title") String title) {
		return () -> ResponseEntity.ok(ApiResponse.<List<T>>builder().data(getBaseService().findByTitle(title)).build());
	}

	@GetMapping(path= "search", params = {"title"})
	public Callable<ResponseEntity<ApiResponse<List<T>>>> findByTitleContainingIgnoreCase(@RequestParam("title") String title) {
		return () -> ResponseEntity.ok(ApiResponse.<List<T>>builder()
											.data(getBaseService().findByTitleContainingIgnoreCase(title))
											.build());
	}
}