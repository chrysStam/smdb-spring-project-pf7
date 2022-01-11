package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.Film;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends ContentRepository<Film> {
}
