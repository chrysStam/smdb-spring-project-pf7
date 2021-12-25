package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.TVShow;

import gr.codelearn.smdb.api.transfer.KeyValue;

import java.util.List;

public interface TVShowService extends BaseService<TVShow, Long> {
	// Long getNumOfShowsByGenre(Genre genre);

	// For each year, get num of shows of that genre
	// List<KeyValue<Integer,Long>> getNumOfShowsPerYearByGenre(Genre genre);


}
