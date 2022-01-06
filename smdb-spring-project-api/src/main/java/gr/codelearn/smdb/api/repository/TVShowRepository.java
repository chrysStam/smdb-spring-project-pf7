package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.domain.TVShow;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TVShowRepository extends JpaRepository<TVShow, Long> {
	TVShow findByTitle(String title);

	@Query("select DISTINCT s from TVShow s WHERE lower(s.title) LIKE %:title% ")
	List<TVShow> searchByTitle(String title);


	@Query("select DISTINCT s from TVShow s ORDER BY s.imdbScore DESC ")
	List<TVShow> findTopRating(PageRequest pageable);



	@Query("select DISTINCT s from TVShow s JOIN s.contentContributors c JOIN c.person p where p.id =(select DISTINCT" +
			" p" +
			".id from Person p WHERE p.name = :name and p.surname=:surname)")
	List<TVShow> findByContributor(String name,String surname);

	@Query("select DISTINCT s from TVShow s JOIN s.contentContributors c JOIN c.key k JOIN c.person p where p.id =" +
			"(select DISTINCT p.id from Person p WHERE p.name = :name and p.surname=:surname) AND (k.role=:role) ")
	List<TVShow> findByContributorAndRole(String name, String surname, Role role);


	List<TVShow> findAllByGenresIn(Set<Genre> genre);
}
