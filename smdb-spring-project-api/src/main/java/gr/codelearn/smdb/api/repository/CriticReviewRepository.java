package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.CriticReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriticReviewRepository extends JpaRepository<CriticReview, Long> {
	List<CriticReview> findByAuthor(String author);

	List<CriticReview> findByRatingGreaterThanEqual(Double minRating);

	List<CriticReview> findByRatingLessThanEqual(Double maxRating);

	List<CriticReview> findByRatingBetween(Double minRating, Double maxRating);
}
