package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.domain.CriticReview;
import gr.codelearn.smdb.api.service.BaseService;
import gr.codelearn.smdb.api.service.CriticReviewService;
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
@RequestMapping("/reviews")
public class CriticReviewController extends AbstractController<CriticReview> {
	private final CriticReviewService criticReviewService;

	@Override
	protected BaseService<CriticReview, Long> getBaseService() {
		return criticReviewService;
	}

	@GetMapping(params = {"author"})
	public Callable<ResponseEntity<ApiResponse<List<CriticReview>>>> findByAuthor(@RequestParam("author") String author) {
		return () -> ResponseEntity.ok(
				ApiResponse.<List<CriticReview>>builder().data(criticReviewService.findByAuthor(author)).build());
	}

	@GetMapping(params = {"minRating"})
	public Callable<ResponseEntity<ApiResponse<List<CriticReview>>>> findByRatingGreaterThanEqual(
			@RequestParam("minRating") Double minRating) {
		return () -> ResponseEntity.ok(ApiResponse.<List<CriticReview>>builder()
											.data(criticReviewService.findByRatingGreaterThanEqual(minRating)).build());
	}

	@GetMapping(params = {"maxRating"})
	public Callable<ResponseEntity<ApiResponse<List<CriticReview>>>> findByRatingLessThanEqual(
			@RequestParam("maxRating") Double maxRating) {
		return () -> ResponseEntity.ok(
				ApiResponse.<List<CriticReview>>builder().data(criticReviewService.findByRatingLessThanEqual(maxRating))
						   .build());
	}

	@GetMapping(params = {"minRating", "maxRating"})
	public Callable<ResponseEntity<ApiResponse<List<CriticReview>>>> findByRatingBetween(
			@RequestParam("minRating") Double minRating, @RequestParam("maxRating") Double maxRating) {
		return () -> ResponseEntity.ok(ApiResponse.<List<CriticReview>>builder()
											.data(criticReviewService.findByRatingBetween(minRating, maxRating))
											.build());
	}
}
