package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	@Query("select c from Content c join c.contentContributors cC where cC.key.personId = ?1 and cC.key.role = ?2")
	List<Content> getContributionsOfPersonByIdByRole(Long personId, Role role);
}
