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

	//	 Report 1: Return the top X high-rated content.
	public List<Content> getTopXHighIMDBScore(Integer top){
		return reportRepository.findTopXHighIMDBScore(PageRequest.of(0, top));
	}

	//	Report 2: Return all content associated with a given individual regardless of hir/her contributing role.
	//	BY FULL NAME
	public List<Content> getAllContentByContributorByFullName(String name,String surname){
		return reportRepository.findByContributorByFullName(name,surname);
	}
	// BY ID
	 public List<Content> getAllContentByContributorById(Long id){
		return reportRepository.findContributionsOfPersonById(id);
	 }

	//	Report 3: Return all content associated with a given individual for a given contributing role.
	// BY ID
	 public List<Content> getAllContentByContributorByIdAndRole(Long id, Role role){
		return reportRepository.findContributionsOfPersonByIdAndRole(id, role);
	 }

	//	BY FULL NAME
	public List<Content> getAllContentByContributorByFullNameAndRole(String name,String surname,Role role){
		return reportRepository.findByContributorByFullNameAndRole(name,surname,role);
	}

	//	Report 4: Return all content for a given genre
	public List<Content> getAllContentByGenre(Genre genre) {
		return reportRepository.findAllByGenresContaining(genre);
	}

	//	Report 5: Return the number of shows per genre
	public List<NoOfContentPerGenreDto> getNoOfContentPerGenre(){
		return reportRepository.findNoOfContentPerGenre();
	}

	//	Report 6: Return the numbers of shows per year per genre
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

	//	Report 7: Return all content associated with a given individual organized per genre
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

