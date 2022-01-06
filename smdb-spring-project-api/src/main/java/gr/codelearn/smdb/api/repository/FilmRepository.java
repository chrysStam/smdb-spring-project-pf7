package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.domain.TVShow;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

	Film findByTitle(String title);
	@Query("select DISTINCT f from Film f WHERE lower(f.title) LIKE %:title% ")
	List<Film> searchByTitle(String title);



	@Query("select DISTINCT f from Film f ORDER BY f.imdbScore DESC ")
	List<Film> findTopRating(PageRequest pageable);



	@Query("select DISTINCT f from Film f JOIN f.contentContributors c JOIN c.person p where p.id =(select DISTINCT p" +
			".id from Person p WHERE p.name = :name and p.surname=:surname)")
	List<Film> findByContributor(String name,String surname);

	@Query("select DISTINCT f from Film f JOIN f.contentContributors c JOIN c.key k JOIN c.person p where p.id =" +
			"(select DISTINCT p.id from Person p WHERE p.name = :name and p.surname=:surname) AND (k.role=:role) ")
	List<Film> findByContributorAndRole(String name, String surname, Role role);

	List<Film> findAllByGenresIn(Set<Genre> genre);


}
