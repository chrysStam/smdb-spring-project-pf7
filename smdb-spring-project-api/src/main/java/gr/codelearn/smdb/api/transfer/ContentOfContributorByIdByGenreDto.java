package gr.codelearn.smdb.api.transfer;

import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Role;

public interface ContentOfContributorByIdByGenreDto {
	Long	getPersonId();
	Role	getRole();
	Long 	getContentId();
	String	getTitle();
	String 	getMPR();
	Double	getImdbScore();
	Genre	getGenres();
	Integer getReleaseYear();
	String 	getLanguage();
	String	getPlotSummary();

}
