package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.ContentContributor;
import gr.codelearn.smdb.api.domain.CriticReview;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.domain.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Content,Long> {

	List<Content> findByTitleContainingIgnoreCaseOrPlotSummaryContainingIgnoreCase(String title,String plotSummary);

	@Query("select distinct p from Person p where p.name like %:name% or p.surname like %:surname%")
	List<Person> searchInPerson(String name, String surname);

	@Query("select distinct cr from CriticReview cr where (cr.author like %:keyword%) or (cr.body like %:keyword%)")
	List<CriticReview> searchInCriticReview(String keyword);

}
