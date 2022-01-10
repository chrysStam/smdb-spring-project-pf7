package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.helpers.YearGenresStats;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.transfer.NoOfContentPerGenreDto;

import java.util.List;

public interface ReportService {

	List<Content> getTopXHighIMDBScore(Integer top); // List of relevant Films and TVShows (only relevant attributes?)
	List<Content> getByContributorByName(String name,String surname);
	List<Content> getByContributorByNameAndRole(String name, String surname, Role role);
	List<Content> getAllContentByGenre(Genre genre);
	List<NoOfContentPerGenreDto> getNoOfContentPerGenre();
	List<YearGenresStats> getNoOfContentPerYearPerGenre();

	// Content of person, grouped by genres
	// List<Content> getAllContentOfContributorByIdPerGenres(Long personId);

	List<Content> searchByTitle(String title);

}
