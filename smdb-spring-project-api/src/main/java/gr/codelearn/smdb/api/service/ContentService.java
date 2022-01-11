package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.CriticReview;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;

import java.util.List;

public interface ContentService<T extends Content> extends BaseService<T, Long> {
	void addGenre(T content, Genre genre);

	void addContributor(T content, Person person, Role role);

	void addCriticReview(T content, CriticReview criticReview);

	List<T> findByTitle(String title);
}
