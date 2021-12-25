package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.transfer.KeyValue;

import java.util.List;

public interface FilmService extends BaseService<Film, Long> {
	// void addGenre(Film film, Genre genre);

	// void addContributor(Film film, Person person, Role role);

	// For each year, get num of films of that genre
	// List<KeyValue<Integer,Long>> getNumOfFilmsPerYearPerGenre(Genre genre);
}
