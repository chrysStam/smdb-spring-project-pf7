package gr.codelearn.smdb.api.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name ="PEOPLE")
@SequenceGenerator(name = "idGenerator", sequenceName = "PEOPLE_SEQ", initialValue = 21, allocationSize = 1)
public class Person extends BaseModel {
	@NotNull
	@Column(length = 20)
	private String name;

	@NotNull
	@Column(length = 30)
	private String surname;

	@NotNull
	@Temporal(TemporalType.DATE)	// YYYY-MM-DD
	private Date birthDate;

	@Temporal(TemporalType.DATE)	// YYYY-MM-DD
	private Date deathDate;

	@JsonManagedReference("contentContributions")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "person", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private final Set<ContentContributor> contentContributors = new HashSet<>();
}
