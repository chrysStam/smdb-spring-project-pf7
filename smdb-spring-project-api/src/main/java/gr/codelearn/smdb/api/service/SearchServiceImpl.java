package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final SearchRepository searchRepository;

	public List<Content> findByTitleContainingIgnoreCaseOrPlotSummaryContainingIgnoreCase(String title,
																						  String plotSummary){
		return searchRepository.findByTitleContainingIgnoreCaseOrPlotSummaryContainingIgnoreCase(title,plotSummary);

	}

	public List<Person> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String name,String surname){
		return searchRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(name,surname);
	}

}
