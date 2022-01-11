package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository<T extends Content> extends JpaRepository<T, Long> {
	List<T> findByTitle(String title);

	List<T> findByTitleContainingIgnoreCase(String title);

	@Query("select DISTINCT s from #{#entityName} s JOIN s.contentContributors c JOIN c.person p where p.id =(select " +
			"DISTINCT p.id from Person p WHERE p.name = :name and p.surname=:surname)")
	List<T> findByContributorByFullName(String name, String surname);
}
