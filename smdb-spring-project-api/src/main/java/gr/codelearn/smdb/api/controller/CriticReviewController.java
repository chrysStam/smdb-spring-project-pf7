package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.domain.CriticReview;
import gr.codelearn.smdb.api.service.BaseService;
import gr.codelearn.smdb.api.service.CriticReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class CriticReviewController extends AbstractController<CriticReview> {
	private final CriticReviewService criticReviewService;

	@Override
	protected BaseService<CriticReview, Long> getBaseService() {
		return criticReviewService;
	}
}
