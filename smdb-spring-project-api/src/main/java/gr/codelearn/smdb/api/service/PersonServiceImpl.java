package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.domain.TVShow;
import gr.codelearn.smdb.api.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl extends BaseServiceImpl<Person> implements PersonService {
	private final PersonRepository personRepository;

	@Override
	public JpaRepository<Person, Long> getRepository() {
		return personRepository;
	}

	public Person getPersonWithMostContributions(){
		return personRepository.findPersonWithTheMostContributions();
	}

	public  List<Person> getPeopleOfSpecificContent(Long contentId){
		return  personRepository.findPeopleOfSpecificContent(contentId);
	}

	public  List<Person> getPeopleByContributionRole(Role role){
		return  personRepository.findPeopleByContributionRole(role.toString());
	}

}
