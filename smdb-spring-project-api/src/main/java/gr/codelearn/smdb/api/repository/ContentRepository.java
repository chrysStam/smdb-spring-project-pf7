package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
	@Query("select count(genres) from Content c inner join c.genres genres")
	Long countContentGenres();

	@Query("select count(contentContributors) from Content c inner join c.contentContributors contentContributors")
	Long countContentContributors();
}
