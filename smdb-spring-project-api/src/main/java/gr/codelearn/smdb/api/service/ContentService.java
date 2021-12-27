package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Genre;

public interface ContentService<T extends Content> extends BaseService<T, Long> {
	void addGenre(T content, Genre genre);

	// void addContributor(Content content, Person person, Role role);
}
