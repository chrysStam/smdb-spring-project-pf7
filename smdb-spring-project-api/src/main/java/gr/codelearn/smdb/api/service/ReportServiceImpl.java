package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.repository.FilmRepository;
import gr.codelearn.smdb.api.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
	private final ReportRepository reportRepository;

	//@Override
	public JpaRepository<Content, Long> getRepository() {
		return reportRepository;
	}

	@Override
	public List<Content> searchByTitle(final String title) {
		return reportRepository.searchByTitle(title);
	}

	@Override
	public List<Content> findTopRatings(final Integer num){
		return reportRepository.findTopRating(PageRequest.of(0, num));
	}

	public List<Content> findByContributor(final String name,final String surname){
		return reportRepository.findByContributor(name,surname);
	}

	public List<Content> findByContributorAndRole(final String name,final String surname,final Role role){
		return reportRepository.findByContributorAndRole(name,surname,role);
	}

	@Override
	public List<Content> findAllByGenresIn(final Set<Genre> genre) {
		return (List<Content>) reportRepository.findAllByGenresIn(genre);
	}

	@Override
	public Integer findNative(){
		return reportRepository.findNative();
	}
}

