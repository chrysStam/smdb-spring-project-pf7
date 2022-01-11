package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.TVShow;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TVShowRepository extends ContentRepository<TVShow> {

	List<TVShow> findAllByGenresIn(Set<Genre> genre);
}
