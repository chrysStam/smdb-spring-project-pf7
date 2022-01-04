package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

	Film findByTitle(String title);
	List<Film> findAllByGenresIn(Set<Genre> genre);

	@Query("select DISTINCT f from Film f ORDER BY f.imdbScore DESC ")
	List<Film> findTopRating(PageRequest pageable);

	@Query("select DISTINCT f from Film f WHERE lower(f.title) LIKE %:title% ")
	List<Film> searchByTitle(String title);
}
