package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl extends ContentServiceImpl<Film> implements FilmService {
	private final FilmRepository filmRepository;

	@Override
	public JpaRepository<Film, Long> getRepository() {
		return filmRepository;
	}
}
