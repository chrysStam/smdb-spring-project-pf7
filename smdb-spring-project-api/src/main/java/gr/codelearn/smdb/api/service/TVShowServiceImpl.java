package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.TVShow;
import gr.codelearn.smdb.api.repository.TVShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TVShowServiceImpl extends BaseServiceImpl<TVShow> implements TVShowService {
	private final TVShowRepository tvShowRepository;

	@Override
	public JpaRepository<TVShow, Long> getRepository() {
		return tvShowRepository;
	}
	
}
