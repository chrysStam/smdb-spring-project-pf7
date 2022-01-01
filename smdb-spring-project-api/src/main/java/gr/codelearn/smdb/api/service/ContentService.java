package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;

public interface ContentService<T extends Content> extends BaseService<T, Long> {
	void addGenre(T content, Genre genre);

	void addContributor(T content, Person person, Role role);
}
