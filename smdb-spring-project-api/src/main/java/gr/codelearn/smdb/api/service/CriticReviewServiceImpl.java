package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.CriticReview;
import gr.codelearn.smdb.api.repository.CriticReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CriticReviewServiceImpl extends BaseServiceImpl<CriticReview> implements CriticReviewService {
	private final CriticReviewRepository criticReviewRepository;

	@Override
	public JpaRepository<CriticReview, Long> getRepository() {
		return criticReviewRepository;
	}

	@Override
	public List<CriticReview> findByAuthor(String author) {
		return criticReviewRepository.findByAuthor(author);
	}

	@Override
	public List<CriticReview> findByRatingGreaterThanEqual(Double minRating) {
		return criticReviewRepository.findByRatingGreaterThanEqual(minRating);
	}

	@Override
	public List<CriticReview> findByRatingLessThanEqual(Double maxRating) {
		return criticReviewRepository.findByRatingLessThanEqual(maxRating);
	}

	@Override
	public List<CriticReview> findByRatingBetween(Double minRating, Double maxRating) {
		return criticReviewRepository.findByRatingBetween(minRating, maxRating);
	}
}
