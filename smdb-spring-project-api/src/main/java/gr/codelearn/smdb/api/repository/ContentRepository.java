package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository<T extends Content> extends JpaRepository<T, Long> {
	List<T> findByTitle(String title);

	List<T> findByTitleContainingIgnoreCase(String title);
}
