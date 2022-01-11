package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.TVShow;

import java.util.List;
import java.util.Set;

public interface TVShowService extends ContentService<TVShow> {

	List<TVShow> findAllByGenresIn(Set<Genre> genre);
}
