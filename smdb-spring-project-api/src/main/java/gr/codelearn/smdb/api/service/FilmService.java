package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Film;

import java.util.List;

public interface FilmService extends ContentService<Film> {

	List<Film> findTopXOrderedByBoxOffice(Integer num);
}
