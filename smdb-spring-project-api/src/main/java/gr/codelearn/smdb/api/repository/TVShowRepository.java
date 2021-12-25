package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TVShowRepository extends JpaRepository<TVShow, Long> {
}
