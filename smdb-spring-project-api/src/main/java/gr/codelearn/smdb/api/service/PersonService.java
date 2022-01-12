package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.domain.TVShow;

import java.util.HashMap;
import java.util.List;

public interface PersonService extends BaseService<Person, Long> {

	Person getPersonWithMostContributions();

	List<Person> getPeopleOfSpecificContent(Long contentId);

	List<Person> getPeopleByContributionRole(Role role);
}
