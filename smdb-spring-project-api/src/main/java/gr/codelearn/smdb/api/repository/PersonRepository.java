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

	@Query("select tv from TVShow tv join tv.contentContributors tvC where tvC.key.personId = ?1")
	List<TVShow> getTVShowContributionsOfPersonById(Long personId);

	@Query("select f from Film f join f.contentContributors fC where fC.key.personId = ?1")
	List<Film>getFilmContributionsOfPersonById(Long personId);

	@Query(value="Select * from people where id=(Select person_id " +
			"From(SELECT person_id, count(person_id) as contributions " +
			"FROM CONTENT_CONTRIBUTORS group by person_id order by contributions desc limit 1));"
			, nativeQuery=true)
	Person findPersonIdWithTheMostContributions();
}
