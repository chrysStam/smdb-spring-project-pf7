package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.repository.ContentRepository;
import gr.codelearn.smdb.api.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl extends ContentServiceImpl<Film> implements FilmService {
	private final FilmRepository filmRepository;

	@Override
	public ContentRepository<Film> getRepository() {
		return filmRepository;
	}

	@Override
	public List<Film> findTopXOrderedByBoxOffice(final Integer num) {
		return getRepository().findAll(PageRequest.of(0, num, Sort.by(Sort.Direction.DESC, "boxOffice"))).toList();
	}
}
