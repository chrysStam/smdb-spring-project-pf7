package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.TVShow;

import java.util.List;

public interface TVShowService extends ContentService<TVShow> {
	List<TVShow> findByCompletion(Boolean completed);
}
