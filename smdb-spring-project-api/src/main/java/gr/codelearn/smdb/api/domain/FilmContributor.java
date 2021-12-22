package gr.codelearn.smdb.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
@Data
@SuperBuilder
@NoArgsConstructor
@ToString
@Entity
@Table(name = "FILM_CONTRIBUTORS")
@SequenceGenerator(name = "idGenerator", sequenceName = "FILM_CONTRIBUTORS_SEQ", initialValue = 1, allocationSize = 1)
public class FilmContributor implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name = "film_id", referencedColumnName = "id")
	private Film film;

	@Id
	@ManyToOne
	@JoinColumn(name = "person_id", referencedColumnName = "id")
	private Person person;

	@Id
	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;
}
