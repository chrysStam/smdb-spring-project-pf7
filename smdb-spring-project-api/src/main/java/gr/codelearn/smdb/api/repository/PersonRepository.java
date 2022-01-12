package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	@Query(value="Select * from people where id=(Select person_id " +
			"From(SELECT person_id, count(person_id) as contributions " +
			"FROM CONTENT_CONTRIBUTORS group by person_id order by contributions desc limit 1));"
			, nativeQuery=true)
	Person findPersonWithTheMostContributions();

	@Query(value="SELECT DISTINCT p.* FROM CONTENT_CONTRIBUTORS as cc inner join people as p on cc.person_id = p.id " +
			"where cc.content_id = ?1", nativeQuery=true)
	List<Person> findPeopleOfSpecificContent(Long contentId);

	@Query(value="SELECT DISTINCT p.* FROM CONTENT_CONTRIBUTORS as cc inner join people as p on cc.person_id = p.id " +
			"where cc.role = ?1", nativeQuery=true)
	List<Person> findPeopleByContributionRole(String role);
}
