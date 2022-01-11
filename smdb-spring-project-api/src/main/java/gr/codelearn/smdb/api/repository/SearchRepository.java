package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Content,Long> {
	List<Content> findByTitleContainingIgnoreCaseOrPlotSummaryContainingIgnoreCase(String title,String plotSummary);

	@Query("select distinct p from Person p where p.name like %:name% or p.surname like %:surname%")
	List<Person> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String name, String surname);
}
