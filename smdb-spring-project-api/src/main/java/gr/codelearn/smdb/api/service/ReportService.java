package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface ReportService {
	// List<Content> getTopXHighRated(Integer top); // List of relevant Films and TVShows (only relevant attributes?)
	// List<Content> getAllContentByGenre(Genre genre);

	// Content of person, grouped by genres
	// List<Content> getAllContentOfContributorByIdPerGenres(Long personId);

	List<Content> searchByTitle(String title);
	List<Content> findTopRatings(Integer num);

	List<Content> findByContributor(String name,String surname);
	List<Content> findByContributorAndRole(String name, String surname, Role role);
	List<Content> findAllByGenresIn(Set<Genre> genre);


	//Native Query Find all Content
	Integer findNative();


}
