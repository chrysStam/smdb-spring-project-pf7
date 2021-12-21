package gr.codelearn.smdb.api.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name ="PEOPLE")
@SequenceGenerator(name = "idGenerator", sequenceName = "PEOPLE_SEQ", initialValue = 1, allocationSize = 1)
public class Person extends BaseModel {

	@NotNull
	private String name;
	@NotNull
	private String surname;

	@NotNull
	private Date birthDate;
	private Date deathDate;

	@OneToMany(mappedBy = "person")
	private Set<FilmContributor> filmContributions;
}
