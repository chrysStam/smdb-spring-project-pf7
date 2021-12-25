package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl extends BaseServiceImpl<Film> implements FilmService {
	private final FilmRepository filmRepository;

	@Override
	public JpaRepository<Film, Long> getRepository() {
		return filmRepository;
	}

	/*@Override
	public void addGenre(Film film, Genre genre) {
		if (checkNull(film, genre)) {
			return;
		}

		// If genre is already contained in film, this does nothing
		boolean isNew = film.getGenres().add(genre);
		if (isNew) {
			logger.debug("Added Genre [{}] to Film [{}]", genre, film);
		}
		else {
			logger.warn("Genre [{}] already in Film [{}], nothing was changed.", genre, film);
		}
	}

	@Override
	public void addContributor(Film film, Person person, Role role) {

	}

	private boolean checkNull(Film film, Genre genre) {
		if (film == null) {
			logger.warn("Film is null");
			return true;
		}
		if (genre == null) {
			logger.warn("Genre is null");
			return true;
		}
		return false;
	}*/
}
