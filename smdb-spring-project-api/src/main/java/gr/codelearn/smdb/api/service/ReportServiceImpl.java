package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.repository.FilmRepository;
import gr.codelearn.smdb.api.repository.ReportRepository;
import gr.codelearn.smdb.api.transfer.NoOfContentPerGenreDto;
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

	public List<Content> getTopXHighIMDBScore(final Integer top){
		return reportRepository.findTopRating(PageRequest.of(0, top));
	}

	public List<Content> getByContributorByName(final String name,final String surname){
		return reportRepository.findByContributorByName(name,surname);
	}

	public List<Content> getByContributorByNameAndRole(final String name,final String surname,final Role role){
		return reportRepository.findByContributorByNameAndRole(name,surname,role);
	}

	public List<Content> getAllContentByGenre(final Genre genre) {
		return (List<Content>) reportRepository.findAllByGenresContaining(genre);
	}

	public List<NoOfContentPerGenreDto> getNoOfContentPerGenre(){
		return reportRepository.findNoOfContentPerGenre();
	}
}

