package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.base.AbstractLogComponent;
import gr.codelearn.smdb.api.domain.BaseModel;
import gr.codelearn.smdb.api.service.BaseService;
import gr.codelearn.smdb.api.transfer.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.Callable;

public abstract class AbstractController<T extends BaseModel> extends AbstractLogComponent {
	protected abstract BaseService<T, Long> getBaseService();

	@GetMapping("/{id}")
	public Callable<ResponseEntity<ApiResponse<T>>> get(@PathVariable("id") final Long id) {
		return () -> ResponseEntity.ok(ApiResponse.<T>builder().data(getBaseService().find(id)).build());
	}

	@GetMapping
	public Callable<ResponseEntity<ApiResponse<List<T>>>> findAll() {
		return () -> ResponseEntity.ok(ApiResponse.<List<T>>builder().data(getBaseService().findAll()).build());
	}

	@PostMapping
	public Callable<ResponseEntity<ApiResponse<T>>> create(@Valid @RequestBody final T entity) {
		return () -> new ResponseEntity<>(ApiResponse.<T>builder().data(getBaseService().create(entity)).build(),
										  getNoCacheHeaders(), HttpStatus.CREATED);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Valid @RequestBody final T entity) {
		getBaseService().update(entity);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") final Long id) {
		getBaseService().deleteById(id);
	}

	protected HttpHeaders getNoCacheHeaders() {
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		return headers;
	}
}
