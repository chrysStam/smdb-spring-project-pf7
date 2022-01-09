package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface ReportService {
	 List<Content> getTopXHighIMDBScore(Integer top); // List of relevant Films and TVShows (only relevant attributes?)
	 List<Content> getAllContentByGenre(Genre genre);

	// Content of person, grouped by genres
	// List<Content> getAllContentOfContributorByIdPerGenres(Long personId);

	List<Content> searchByTitle(String title);
	List<Content> getByContributorByName(String name,String surname);
	List<Content> getByContributorByNameAndRole(String name, String surname, Role role);

}
