package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.domain.TVShow;
import gr.codelearn.smdb.api.repository.ContentRepository;
import gr.codelearn.smdb.api.repository.TVShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TVShowServiceImpl extends ContentServiceImpl<TVShow> implements TVShowService {
	private final TVShowRepository tvShowRepository;

	@Override
	public ContentRepository<TVShow> getRepository() {
		return tvShowRepository;
	}

	public List<TVShow> findByContributorByFullName(final String name, final String surname) {
		return tvShowRepository.findByContributorByFullName(name, surname);
	}

	public List<TVShow> findByContributorAndRoleFullName(final String name, final String surname, final Role role) {
		return tvShowRepository.findByContributorAndRoleFullName(name, surname, role);
	}

	@Override
	public List<TVShow> findAllByGenresIn(final Set<Genre> genre) {
		return tvShowRepository.findAllByGenresIn(genre);
	}
}
