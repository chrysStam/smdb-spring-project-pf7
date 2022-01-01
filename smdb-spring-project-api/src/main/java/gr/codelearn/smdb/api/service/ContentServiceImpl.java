package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.ContentContributor;
import gr.codelearn.smdb.api.domain.ContentContributorKey;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;

public abstract class ContentServiceImpl<T extends Content> extends BaseServiceImpl<T> implements ContentService<T> {

	@Override
	public void addGenre(T content, Genre genre) {
		// Return if any parameter is null
		if (content == null) {logger.warn("Content is null"); return;}
		if (genre == null) {logger.warn("Genre is null"); return;}

		// If genre is already contained in content, do nothing
		boolean isNew = content.getGenres().add(genre);
		if (isNew) {
			logger.debug("Added Genre [{}] to Content [{}]", genre, content);
		}
		else {
			logger.warn("Genre [{}] already in Content [{}], nothing was changed.", genre, content);
		}
	}

	@Override
	public void addContributor(T content, Person person, Role role) {
		// Return if any parameter is null
		if (content == null) {logger.warn("Content is null"); return;}
		if (person == null) {logger.warn("Person is null"); return;}
		if (role == null) {logger.warn("Role is null"); return;}

		// If the tuple (person,role) is already contained in content, do nothing
		boolean isNew = content.getContentContributors().add(ContentContributor.builder()
			  .key(ContentContributorKey.builder()
					.role(role).build())
			  .content(content)
			  .person(person).build());
		if (isNew) {
			logger.debug("Added Person [{}] with Role [{}] as contributor to Content [{}]", person, role,
						 content);
		}
		else {
			logger.warn("Person [{}] with Role [{}] already contributing in Content [{}], nothing was changed.",
						person, role, content);
		}
	}
}
