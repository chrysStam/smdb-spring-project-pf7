package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.TVShow;
import gr.codelearn.smdb.api.helpers.MultiSearch;
import gr.codelearn.smdb.api.helpers.MultiSearch2;
import org.springframework.data.jpa.repository.Query;

import java.util.HashMap;
import java.util.List;

public interface SearchService {

	List<MultiSearch> multiSearch(String keyword);

	List<MultiSearch2> multiSearch2(String keyword);
}
