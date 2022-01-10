package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.Content;
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

	@Query("select DISTINCT f from Content f WHERE lower(f.title) LIKE %:title% ")
	List<Content> searchByTitle(String title);

	@Query("select DISTINCT f from Content f ORDER BY f.imdbScore DESC ")
	List<Content> findTopRating(PageRequest pageable);

	@Query("select DISTINCT f from Content f JOIN f.contentContributors c JOIN c.person p where p.id =(select " +
			"DISTINCT p" +
			".id from Person p WHERE p.name = :name and p.surname=:surname)")
	List<Content> findByContributorByFullName(String name,String surname);

	@Query("select DISTINCT f from Content f JOIN f.contentContributors c JOIN c.key k JOIN c.person p where p.id =" +
			"(select DISTINCT p.id from Person p WHERE p.name = :name and p.surname=:surname) AND (k.role=:role) ")
	List<Content> findByContributorAndRoleByFullName(String name, String surname, Role role);

	List<Content> findAllByGenresIn(Set<Genre> genre);


}
