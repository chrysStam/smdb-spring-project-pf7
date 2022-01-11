package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.repository.ContentRepository;
import gr.codelearn.smdb.api.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl extends ContentServiceImpl<Film> implements FilmService {
	private final FilmRepository filmRepository;

	@Override
	public ContentRepository<Film> getRepository() {
		return filmRepository;
	}

	@Override
	public List<Content> findTopRatings(final Integer num) {
		return filmRepository.findTopRating(PageRequest.of(0, num));
	}

	public List<Content> findByContributorByFullName(final String name, final String surname) {
		return filmRepository.findByContributorByFullName(name, surname);
	}

	public List<Content> findByContributorAndRoleByFullName(final String name, final String surname, final Role role) {
		return filmRepository.findByContributorAndRoleByFullName(name, surname, role);
	}

	@Override
	public List<Content> findAllByGenresIn(final Set<Genre> genre) {
		return (List<Content>) filmRepository.findAllByGenresIn(genre);
	}

}
