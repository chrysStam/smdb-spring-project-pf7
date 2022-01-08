package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl extends ContentServiceImpl<Film> implements FilmService {
	private final FilmRepository filmRepository;

	@Override
	public JpaRepository<Film, Long> getRepository() {
		return filmRepository;
	}



	@Override
	public Film findByTitle(final String title) {
		return filmRepository.findAll().stream().filter(c -> c.getTitle().equals(title)).findAny().orElse(null);
	}

	@Override
	public List<Content> searchByTitle(final String title) {
		return filmRepository.searchByTitle(title);
	}

	@Override
	public List<Content> findTopRatings(final Integer num){
		return filmRepository.findTopRating(PageRequest.of(0, num));
	}

	public List<Content> findByContributor(final String name,final String surname){
		return filmRepository.findByContributor(name,surname);
	}

	public List<Content> findByContributorAndRole(final String name,final String surname,final Role role){
		return filmRepository.findByContributorAndRole(name,surname,role);
	}

	@Override
	public List<Content> findAllByGenresIn(final Set<Genre> genre) {
		return (List<Content>) filmRepository.findAllByGenresIn(genre);
	}

}
