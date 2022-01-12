package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.TVShow;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TVShowRepository extends ContentRepository<TVShow> {
	List<TVShow> findByEndYearNull();

	List<TVShow> findByEndYearNotNull();
}
