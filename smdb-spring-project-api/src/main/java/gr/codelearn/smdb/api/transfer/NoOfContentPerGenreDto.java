package gr.codelearn.smdb.api.transfer;

import gr.codelearn.smdb.api.domain.Genre;

public interface NoOfContentPerGenreDto {
	Genre getGenres();
	Integer getNumber();
}
