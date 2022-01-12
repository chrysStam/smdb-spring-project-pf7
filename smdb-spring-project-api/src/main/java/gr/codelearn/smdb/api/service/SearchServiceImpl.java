package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.BaseModel;
import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.CriticReview;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.helpers.MultiSearch;
import gr.codelearn.smdb.api.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final SearchRepository searchRepository;

	public List<MultiSearch> multiSearch(String keyword, Boolean grouped){

		List<Content> contList =
				searchRepository.findByTitleContainingIgnoreCaseOrPlotSummaryContainingIgnoreCase(keyword,keyword);

		List<Person> personList = searchRepository.searchInPerson(keyword,keyword);

		List<CriticReview> criticReviewList = searchRepository.searchInCriticReview(keyword);

		List<MultiSearch> results = new ArrayList<>();

		if(grouped) {
			if (!contList.isEmpty()) {
				MultiSearch<List<Content>> elemFilm = new MultiSearch<>();
				MultiSearch<List<Content>> elemTvShow = new MultiSearch<>();
				List<Content> filmList = new ArrayList<>();
				List<Content> tvShowList = new ArrayList<>();

				for (Content content : contList) {
					if (content instanceof Film) {
						filmList.add(content);
					} else {
						tvShowList.add(content);
					}
				}

				if (!filmList.isEmpty()) {
					elemFilm.setType("FILM");
					elemFilm.setInfos(filmList);
				}

				if (!tvShowList.isEmpty()) {
					elemTvShow.setType("TVSHOW");
					elemTvShow.setInfos(tvShowList);
				}

				results.add(elemFilm);
				results.add(elemTvShow);

			}

			if (!personList.isEmpty()) {
				MultiSearch<List<Person>> elem = new MultiSearch<>();
				elem.setType("PERSON");
				elem.setInfos(personList);
				results.add(elem);
			}
			if (!criticReviewList.isEmpty()) {
				MultiSearch<List<CriticReview>> elem = new MultiSearch<>();
				elem.setType("CRITIC REVIEW");
				elem.setInfos(criticReviewList);
				results.add(elem);
			}

		}
		else{
			if (!contList.isEmpty()){
				for (Content content:contList ) {
					if (content instanceof Film){
						MultiSearch<Content> elemFilm = new MultiSearch<>();
						elemFilm.setType("FILM");
						elemFilm.setInfos(content);
						results.add(elemFilm);
					}else {
						MultiSearch<Content> elemTvShow = new MultiSearch<>();
						elemTvShow.setType("TVSHOW");
						elemTvShow.setInfos(content);
						results.add(elemTvShow);
					}
				}

			}

			if(!personList.isEmpty()){
				for (Person person: personList) {
					MultiSearch<Person> elem = new MultiSearch<>();
					elem.setType("PERSON");
					elem.setInfos(person);
					results.add(elem);
				}
			}
			if(!criticReviewList.isEmpty()){
				for (CriticReview criticReview: criticReviewList) {
					MultiSearch<CriticReview> elem = new MultiSearch<>();
					elem.setType("CRITIC REVIEW");
					elem.setInfos(criticReview);
					results.add(elem);
				}
			}
		}

		return results;
	}

}
