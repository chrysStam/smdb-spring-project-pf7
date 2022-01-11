package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.TVShow;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SearchService {
	List<Content> findByTitleContainingIgnoreCaseOrPlotSummaryContainingIgnoreCase(String title, String plotSummary);
	List<Person> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String name, String surname);
}
