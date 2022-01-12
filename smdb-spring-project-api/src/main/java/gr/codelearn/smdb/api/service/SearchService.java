package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.helpers.MultiSearch;

import java.util.List;

public interface SearchService {

	List<MultiSearch<?>> multiSearch(String keyword, Boolean grouped);
}
