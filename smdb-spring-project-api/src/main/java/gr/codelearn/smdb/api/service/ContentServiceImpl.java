package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.ContentContributor;
import gr.codelearn.smdb.api.domain.ContentContributorKey;
import gr.codelearn.smdb.api.domain.CriticReview;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.repository.ContentRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class ContentServiceImpl<T extends Content> extends BaseServiceImpl<T> implements ContentService<T> {

	@Override
	public abstract ContentRepository<T> getRepository();

	@Override
	public void addGenre(T content, Genre genre) {
		// Return if any parameter is null
		if (content == null) {
			logger.warn("Content is null");
			return;
		}
		if (genre == null) {
			logger.warn("Genre is null");
			return;
		}

		// If genre is already contained in content, do nothing
		boolean isNew = content.getGenres().add(genre);
		if (isNew) {
			logger.debug("Added Genre [{}] to Content [{}]", genre, content);
		} else {
			logger.warn("Genre [{}] already in Content [{}], nothing was changed.", genre, content);
			throw new DataIntegrityViolationException(
					"Genre [" + genre + "] already in Content with id [" + content.getId() + "], nothing was changed");
		}
	}

	@Override
	public void deleteGenre(T content, Genre genre) {
		// Return if any parameter is null
		if (content == null) {
			logger.warn("Content is null");
			return;
		}
		if (genre == null) {
			logger.warn("Genre is null");
			return;
		}

		// If genre is not contained in content, do nothing
		boolean exists = content.getGenres().contains(genre);
		if (exists) {
			content.getGenres().remove(genre);
			logger.debug("Deleted Genre [{}] from Content [{}]", genre, content);
		} else {
			logger.warn("Genre [{}] is not in Content [{}], nothing was deleted.", genre, content);
			throw new NoSuchElementException(
					"Genre [" + genre + "]  is not in Content with id [" + content.getId() + "], nothing was deleted.");
		}
	}

	@Override
	public void addContributor(T content, Person person, Role role) {
		// Return if any parameter is null
		if (content == null) {
			logger.warn("Content is null");
			return;
		}
		if (person == null) {
			logger.warn("Person is null");
			return;
		}
		if (role == null) {
			logger.warn("Role is null");
			return;
		}

		// If the tuple (person,role) is an existing contributor, do nothing
		boolean isNew = content.getContentContributors().add(ContentContributor.builder().key(
				ContentContributorKey.builder().role(role).build()).content(content).person(person).build());
		if (isNew) {
			logger.debug("Added Person [{}] with Role [{}] as contributor to Content [{}]", person, role, content);
		} else {
			logger.warn("Person [{}] with Role [{}] already contributing in Content [{}], nothing was changed.", person,
						role, content);
			throw new DataIntegrityViolationException(
					"Person with id [" + person.getId() + "]  with Role [" + role + "] already in Content with id [" +
							content.getId() + "], nothing was changed.");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void deleteContributor(T content, Person person, Role role) {
		// Return if any parameter is null
		if (content == null) {
			logger.warn("Content is null");
			return;
		}
		if (person == null) {
			logger.warn("Person is null");
			return;
		}
		if (role == null) {
			logger.warn("Role is null");
			return;
		}

		// If the tuple (person,role) is not an existing contributor, do nothing
		ContentContributor contentContributor = ContentContributor.builder().key(ContentContributorKey.builder().role(
																		  role).contentId(content.getId()).personId(person.getId()).build()).content(content).person(person)
																  .build();
		boolean exists = content.getContentContributors().contains(contentContributor);
		if (exists) {
			getRepository().deleteContributor(content.getId(), person.getId(), role);
			logger.debug("Deleted Person [{}] with Role [{}] as contributor from Content [{}]", person, role, content);
		} else {
			logger.warn("Person [{}] with Role [{}] does not contribute in Content [{}], nothing was deleted.", person,
						role, content);
			throw new NoSuchElementException(
					"Person with id [" + person.getId() + "]  with Role [" + role + "] does not contribute in Content" +
							" with id [" + content.getId() + "], nothing was deleted.");
		}
	}

	@Override
	public void addCriticReview(T content, CriticReview criticReview) {
		// Return if any parameter is null
		if (content == null) {
			logger.warn("Content is null");
			return;
		}
		if (criticReview == null) {
			logger.warn("CriticReview is null");
			return;
		}

		criticReview.setContent(content);
		criticReview.setSubmittedAt(new Date());

		// If criticReview is an existing review, do nothing
		boolean isNew = content.getCriticReviews().add(criticReview);
		if (isNew) {
			logger.debug("Added CriticReview [{}] for Content [{}]", criticReview, content);
		} else {
			logger.warn("CriticReview [{}] for Content [{}] already exists, nothing was changed.", criticReview,
						content);
		}
	}

	@Override
	public List<T> findByTitle(final String title) {
		return getRepository().findByTitle(title);
	}

	@Override
	public List<T> findByTitleContainingIgnoreCase(final String title) {
		return getRepository().findByTitleContainingIgnoreCase(title);
	}

	@Override
	public List<T> findTopXOrderedByImdbScore(final Integer num) {
		return getRepository().findAll(PageRequest.of(0, num, Sort.by(Sort.Direction.DESC, "imdbScore"))).toList();
	}

	@Override
	public List<T> findByContributorByFullName(final String name, final String surname) {
		return getRepository().findByContributorByFullName(name, surname);
	}

	public List<T> findByContributorByFullNameAndRole(final String name, final String surname, final Role role) {
		return getRepository().findByContributorByFullNameAndRole(name, surname, role);
	}

	@Override
	public List<T> findAllByGenresContaining(final Genre genre) {
		return getRepository().findAllByGenresContaining(genre);
	}
}
