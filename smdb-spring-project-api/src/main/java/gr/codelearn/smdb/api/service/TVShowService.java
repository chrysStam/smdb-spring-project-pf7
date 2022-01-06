package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.domain.TVShow;

import gr.codelearn.smdb.api.transfer.KeyValue;

import java.util.List;
import java.util.Set;

public interface TVShowService extends ContentService<TVShow> {
	// Long getNumOfShowsByGenre(Genre genre);

	// For each year, get num of shows of that genre
	// List<KeyValue<Integer,Long>> getNumOfShowsPerYearByGenre(Genre genre);

	TVShow findByTitle(String title);
	List<TVShow> searchByTitle(String title);



	List<TVShow> findTopRatings(Integer num);
	List<TVShow> findByContributor(String name,String surname);
	List<TVShow> findByContributorAndRole(String name, String surname, Role role);
	List<TVShow> findAllByGenresIn(Set<Genre> genre);

}
