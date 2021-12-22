package gr.codelearn.smdb.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TV_SHOW_CONTRIBUTORS")
@SequenceGenerator(name = "idGenerator", sequenceName = "TV_SHOW_CONTRIBUTORS_SEQ", initialValue = 1, allocationSize
		= 1)
public class TVShowContributor implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name = "tv_show_id", referencedColumnName = "id")
	private TVShow tvShow;

	@Id
	@ManyToOne
	@JoinColumn(name = "person_id", referencedColumnName = "id")
	private Person person;

	@Id
	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;
}
