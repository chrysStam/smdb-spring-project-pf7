package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.ContentContributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentContributorRepository extends JpaRepository<ContentContributor, Long> {
}
