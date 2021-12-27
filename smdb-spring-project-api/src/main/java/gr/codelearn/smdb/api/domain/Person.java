package gr.codelearn.smdb.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name ="PEOPLE")
@SequenceGenerator(name = "idGenerator", sequenceName = "PEOPLE_SEQ", initialValue = 1, allocationSize = 1)
public class Person extends BaseModel {
	@NotNull
	@Column(length = 20)
	private String name;

	@NotNull
	@Column(length = 30)
	private String surname;

	@NotNull
	@Temporal(TemporalType.DATE)	// DD-MM-YY
	private Date birthDate;

	@Temporal(TemporalType.DATE)	// DD-MM-YY
	private Date deathDate;

	@OneToMany(mappedBy = "person")
	private final Set<ContentContributor> contentContributors = new HashSet<>();
}
