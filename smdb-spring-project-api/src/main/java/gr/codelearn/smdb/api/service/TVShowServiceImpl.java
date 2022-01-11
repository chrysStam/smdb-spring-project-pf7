package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.TVShow;
import gr.codelearn.smdb.api.repository.ContentRepository;
import gr.codelearn.smdb.api.repository.TVShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TVShowServiceImpl extends ContentServiceImpl<TVShow> implements TVShowService {
	private final TVShowRepository tvShowRepository;

	@Override
	public ContentRepository<TVShow> getRepository() {
		return tvShowRepository;
	}
}
