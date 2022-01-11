package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.domain.TVShow;

import java.util.HashMap;
import java.util.List;

public interface PersonService extends BaseService<Person, Long> {
	 List<Content> getContributionsOfPersonById(Long personId); // List of relevant Films and TVShows

	List<Film> getFilmContributionsOfPersonById(Long personId);
	List<TVShow> getTVShowContributionsOfPersonById(Long personId);

	List<Content> getContributionsOfPersonByIdByRole(Long personId, Role role);

}
