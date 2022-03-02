package gr.codelearn.smdb.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.service.PersonService;
import gr.codelearn.smdb.api.transfer.ApiResponse;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
class PersonControllerIntegrationTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	PersonService personService;

	@Captor
	ArgumentCaptor<Long> idArgumentCaptor;

	@Captor
	ArgumentCaptor<Role> roleArgumentCaptor;

	private final Long testId = 1L;
	private final Role testRole = Role.ACTOR;

	/* Abstract Controller inherited endpoint tests */

	/* TODO extend an abstract ControllerIntegrationTest class and test its' endpoints */

	/* getPersonWithMostContributions endpoint tests */
	@Test
	void getPersonWithMostContributions_whenValidRequest_thenReturns200AndProperResponse() throws Exception {
		// Mock data
		Person mockPerson = Person.builder()
				.name("MockName")
				.surname("MockSurname")
				.birthDate(Date.valueOf("1974-06-23"))
			    .deathDate(Date.valueOf("2013-11-24"))
		   .build();

		when(personService.getPersonWithMostContributions()).thenReturn(mockPerson);

		// Call endpoint
		MvcResult mvcResultAsync = mockMvc.perform(get("/people")
					.header("action", "getPersonWithMostContributions"))
			 	.andExpect(request().asyncStarted())
			 	.andExpect(request().asyncResult(notNullValue()))
				.andReturn();

		MvcResult mvcResult = mockMvc.perform(asyncDispatch(mvcResultAsync))
				.andExpect(status().isOk())
				.andReturn();

		// Test inner method calls and arguments
		verify(personService, times(1)).getPersonWithMostContributions();

		// Test response
		ApiResponse<Person> actualTestResponse = objectMapper
				.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});
		ApiResponse<Person> expectedTestResponse = ApiResponse.<Person>builder()
				.apiError(null)
			  	.data(mockPerson)
			  .build();

		assertThat(actualTestResponse.getApiError()).isEqualTo(expectedTestResponse.getApiError());
		assertThat(actualTestResponse.getData().getName()).isEqualTo(expectedTestResponse.getData().getName());
		assertThat(actualTestResponse.getData().getSurname()).isEqualTo(expectedTestResponse.getData().getSurname());
		/*
		 Checking date equality with bad, hardcoded timezone offset
		 to avoid timezone difference issues of legacy java.Date classes
		 TODO migrate to java.time classes
		*/
		long timezoneTimeDifference = 7200000L;
		assertThat(actualTestResponse.getData().getBirthDate().getTime() - timezoneTimeDifference).isEqualTo(expectedTestResponse.getData().getBirthDate().getTime());
		assertThat(actualTestResponse.getData().getDeathDate().getTime() - timezoneTimeDifference).isEqualTo(expectedTestResponse.getData().getDeathDate().getTime());
	}

	/* getPeopleOfSpecificContent endpoint tests */
	@Test
	void getPeopleOfSpecificContent_whenValidRequest_thenReturns200AndProperResponse() throws Exception {
		// Mock data
		List<Person> mockPeople = List.of(Person.builder()
						.name("MockName")
						.surname("MockSurname")
						.birthDate(Date.valueOf("1974-06-23"))
						.deathDate(Date.valueOf("2013-11-24"))
					.build());

		when(personService.getPeopleOfSpecificContent(testId)).thenReturn(mockPeople);

		// Call endpoint
		MvcResult mvcResultAsync = mockMvc.perform(get("/people/contents/{id}", testId)
					   .header("action", "getPeopleOfSpecificContent"))
				  .andExpect(request().asyncStarted())
				  .andExpect(request().asyncResult(notNullValue()))
				  .andReturn();

		MvcResult mvcResult = mockMvc.perform(asyncDispatch(mvcResultAsync))
				 .andExpect(status().isOk())
				 .andReturn();

		// Test inner method calls and arguments
		verify(personService, times(1)).getPeopleOfSpecificContent(idArgumentCaptor.capture());
		assertThat(idArgumentCaptor.getValue()).isEqualTo(testId);

		// Test response
		ApiResponse<List<Person>> actualTestResponse = objectMapper
				.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});
		ApiResponse<List<Person>> expectedTestResponse = ApiResponse.<List<Person>>builder()
				  .apiError(null)
				  .data(mockPeople)
			  	.build();

		assertThat(actualTestResponse.getApiError()).isEqualTo(expectedTestResponse.getApiError());
		assertThat(actualTestResponse.getData()).hasSameSizeAs(mockPeople);
		assertThat(actualTestResponse.getData().get(0).getName()).isEqualTo(expectedTestResponse.getData().get(0).getName());
		assertThat(actualTestResponse.getData().get(0).getSurname()).isEqualTo(expectedTestResponse.getData().get(0).getSurname());
		/*
		 Checking date equality with bad, hardcoded timezone offset
		 to avoid timezone difference issues of legacy java.Date classes
		 TODO migrate to java.time classes
		*/
		long timezoneTimeDifference = 7200000L;
		assertThat(actualTestResponse.getData().get(0).getBirthDate().getTime() - timezoneTimeDifference).isEqualTo(expectedTestResponse.getData().get(0).getBirthDate().getTime());
		assertThat(actualTestResponse.getData().get(0).getDeathDate().getTime() - timezoneTimeDifference).isEqualTo(expectedTestResponse.getData().get(0).getDeathDate().getTime());
	}

	/* getPeopleByContributionRole endpoint tests */
	@Test
	void getPeopleByContributionRole_whenValidRequest_thenReturns200() throws Exception {
		// Mock data
		List<Person> mockPeople = List.of(Person.builder()
						.name("MockName")
						.surname("MockSurname")
						.birthDate(Date.valueOf("1974-06-23"))
						.deathDate(Date.valueOf("2013-11-24"))
					.build());

		when(personService.getPeopleByContributionRole(testRole)).thenReturn(mockPeople);

		// Call endpoint
		MvcResult mvcResultAsync = mockMvc.perform(get("/people")
					.param("role", String.valueOf(testRole))
					.header("action", "getPeopleByContributionRole"))
				  .andExpect(request().asyncStarted())
				  .andExpect(request().asyncResult(notNullValue()))
				  .andReturn();

		MvcResult mvcResult = mockMvc.perform(asyncDispatch(mvcResultAsync))
				 .andExpect(status().isOk())
				 .andReturn();

		// Test inner method calls and arguments
		verify(personService, times(1)).getPeopleByContributionRole(roleArgumentCaptor.capture());
		assertThat(roleArgumentCaptor.getValue()).isEqualTo(testRole);

		// Test response
		ApiResponse<List<Person>> actualTestResponse = objectMapper
				.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});
		ApiResponse<List<Person>> expectedTestResponse = ApiResponse.<List<Person>>builder()
						.apiError(null)
						.data(mockPeople)
					.build();

		assertThat(actualTestResponse.getApiError()).isEqualTo(expectedTestResponse.getApiError());
		assertThat(actualTestResponse.getData()).hasSameSizeAs(mockPeople);
		assertThat(actualTestResponse.getData().get(0).getName()).isEqualTo(expectedTestResponse.getData().get(0).getName());
		assertThat(actualTestResponse.getData().get(0).getSurname()).isEqualTo(expectedTestResponse.getData().get(0).getSurname());
		/*
		 Checking date equality with bad, hardcoded timezone offset
		 to avoid timezone difference issues of legacy java.Date classes
		 TODO migrate to java.time classes
		*/
		long timezoneTimeDifference = 7200000L;
		assertThat(actualTestResponse.getData().get(0).getBirthDate().getTime() - timezoneTimeDifference).isEqualTo(expectedTestResponse.getData().get(0).getBirthDate().getTime());
		assertThat(actualTestResponse.getData().get(0).getDeathDate().getTime() - timezoneTimeDifference).isEqualTo(expectedTestResponse.getData().get(0).getDeathDate().getTime());
	}
}
