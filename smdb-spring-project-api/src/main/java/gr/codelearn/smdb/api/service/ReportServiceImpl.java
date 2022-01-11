package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.helpers.ContributorGenre;
import gr.codelearn.smdb.api.helpers.YearGenresStat;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.repository.ReportRepository;
import gr.codelearn.smdb.api.transfer.ContentOfContributorByIdByGenreDto;
import gr.codelearn.smdb.api.transfer.NoOfContentPerGenreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
	private final ReportRepository reportRepository;

	@Override
	public List<Content> searchByTitle(String title) {
		return reportRepository.searchByTitle(title);
	}

	public List<Content> getTopXHighIMDBScore(Integer top){
		return reportRepository.findTopXHighIMDBScore(PageRequest.of(0, top));
	}

	public List<Content> getAllContentByContributorByFullName(String name,String surname){
		return reportRepository.findByContributorByFullName(name,surname);
	}

	 public List<Content> getAllContentByContributorById(Long id){
		return reportRepository.findContributionsOfPersonById(id);
	 }

	 public List<Content> getAllContentByContributorByIdAndRole(Long id, Role role){
		return reportRepository.findContributionsOfPersonByIdAndRole(id, role);
	 }

	public List<Content> getAllContentByContributorByFullNameAndRole(String name,String surname,Role role){
		return reportRepository.findByContributorByFullNameAndRole(name,surname,role);
	}

	public List<Content> getAllContentByGenre(Genre genre) {
		return reportRepository.findAllByGenresContaining(genre);
	}

	public List<NoOfContentPerGenreDto> getNoOfContentPerGenre(){
		return reportRepository.findNoOfContentPerGenre();
	}

	public List<YearGenresStat> getNoOfContentPerYearPerGenre()  {
		List<Integer> years = reportRepository.findYears();
		List<YearGenresStat> results = new ArrayList<>();

		for (Integer y : years) {
			YearGenresStat elem = new YearGenresStat();
			elem.setStats(reportRepository.findNoOfContentByReleaseYearPerGenre(y));
			elem.setYear(y);
			results.add(elem);
		}
		return results;
	}

	public List<ContributorGenre> getAllContentOfContributorByIdPerGenres(Long personId){
		List<ContributorGenre> results = new ArrayList<>();
		List<ContentOfContributorByIdByGenreDto> tmp;

		for (Genre genre : EnumSet.allOf(Genre.class)) {
			ContributorGenre elem = new ContributorGenre();
			tmp = reportRepository.findContentOfContributorByIdByGenre(personId, genre.name());
			if (!tmp.isEmpty()){
				elem.setGenre(genre);
				elem.setInfo(reportRepository.findContentOfContributorByIdByGenre(personId, genre.name()));
				results.add(elem);
			}
		}
		return results;
	}
}

