package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.CriticReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriticReviewRepository extends JpaRepository<CriticReview, Long> {
	List<CriticReview> findByAuthor(String author);

	List<CriticReview> findByRatingGreaterThanEqualOrderByRating(Double minRating);

	List<CriticReview> findByRatingLessThanEqualOrderByRatingDesc(Double maxRating);

	List<CriticReview> findByRatingBetweenOrderByRatingDesc(Double minRating, Double maxRating);

	@Query("select r from CriticReview r where r.content.id = ?1")
	List<CriticReview> findByContentId(Long contentId);
}
