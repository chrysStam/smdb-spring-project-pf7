package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;

import java.util.List;
import java.util.Set;

public interface FilmService extends ContentService<Film> {

	List<Content> findAllByGenresIn(Set<Genre> genre);
}
