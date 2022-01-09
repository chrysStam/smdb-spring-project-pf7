package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.domain.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	@Query("select c from Content c join c.contentContributors cC where cC.key.personId = ?1 and cC.key.role = ?2")
	List<Content> getContributionsOfPersonByIdByRole(Long personId, Role role);

	@Query("select tv from TVShow tv join tv.contentContributors tvC where tvC.key.personId = ?1")
	List<TVShow> getTVShowContributionsOfPersonById(Long personId);

	@Query("select f from Film f join f.contentContributors fC where fC.key.personId = ?1")
	List<Film>getFilmContributionsOfPersonById(Long personId);

	@Query("select c from Content c join c.contentContributors cC where cC.key.personId = ?1")
	List<Content>getContributionsOfPersonById(Long personId);
}
