package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.base.AbstractLogComponent;
import gr.codelearn.smdb.api.domain.BaseModel;
import gr.codelearn.smdb.api.service.BaseService;
import gr.codelearn.smdb.api.transfer.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public abstract class AbstractController<T extends BaseModel> extends AbstractLogComponent {
	protected abstract BaseService<T, Long> getBaseService();

	@GetMapping
	public ResponseEntity<ApiResponse<List<T>>> findAll() {
		return ResponseEntity.ok(ApiResponse.<List<T>>builder().data(getBaseService().findAll()).build());
	}
}
