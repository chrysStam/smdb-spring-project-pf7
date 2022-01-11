package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.helpers.ContributorGenre;
import gr.codelearn.smdb.api.helpers.YearGenresStat;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.transfer.NoOfContentPerGenreDto;

import java.util.List;

public interface ReportService {

	List<Content> getTopXHighIMDBScore(Integer top); // List of relevant Films and TVShows (only relevant attributes?)

	List<Content> getAllContentByContributorByFullName(String name,String surname);

	List<Content> getAllContentByContributorById(Long id);

	List<Content> getAllContentByContributorByFullNameAndRole(String name, String surname, Role role);

	List<Content> getAllContentByContributorByIdAndRole(Long id, Role role);

	List<Content> getAllContentByGenre(Genre genre);

	List<NoOfContentPerGenreDto> getNoOfContentPerGenre();

	List<YearGenresStat> getNoOfContentPerYearPerGenre();

	List<ContributorGenre> getAllContentOfContributorByIdPerGenres(Long personId);

	List<Content> searchByTitle(String title);
}
