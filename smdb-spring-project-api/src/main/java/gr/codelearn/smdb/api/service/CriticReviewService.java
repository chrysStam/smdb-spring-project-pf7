package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.CriticReview;

import java.util.List;

public interface CriticReviewService extends BaseService<CriticReview, Long> {
	List<CriticReview> findByAuthor(String author);

	List<CriticReview> findByRatingGreaterThanEqual(Double minRating);

	List<CriticReview> findByRatingLessThanEqual(Double maxRating);

	List<CriticReview> findByRatingBetween(Double minRating, Double maxRating);

	List<CriticReview> findByContentId(Long contentId);
}
