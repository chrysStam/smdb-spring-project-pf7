package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.CriticReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriticReviewRepository extends JpaRepository<CriticReview, Long> {
}
