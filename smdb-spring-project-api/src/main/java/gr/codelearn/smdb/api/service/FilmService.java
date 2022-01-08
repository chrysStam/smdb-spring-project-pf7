package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.ContentContributor;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.transfer.KeyValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface FilmService extends ContentService<Film> {

	// For each year, get num of films of that genre
	// List<KeyValue<Integer,Long>> getNumOfFilmsPerYearPerGenre(Genre genre);



	Film findByTitle(String title);
	List<Film> searchByTitle(String title);
	List<Content> findTopRatings(Integer num);

	List<Film> findByContributor(String name,String surname);
	List<Film> findByContributorAndRole(String name,String surname,Role role);
	List<Film> findAllByGenresIn(Set<Genre> genre);

}
