package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Genre;

public abstract class ContentServiceImpl<T extends Content> extends BaseServiceImpl<T> implements ContentService<T> {

	@Override
	public void addGenre(T content, Genre genre) {
		// Return if any parameter is null
		if (content == null) {logger.warn("Content is null"); return;}
		if (genre == null) {logger.warn("Genre is null"); return;}

		// If genre is already contained in film, do nothing
		boolean isNew = content.getGenres().add(genre);
		if (isNew) {
			logger.debug("Added Genre [{}] to Content [{}]", genre, content);
		}
		else {
			logger.warn("Genre [{}] already in Content [{}], nothing was changed.", genre, content);
		}
	}
}
