package gr.codelearn.smdb.api.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TV_SHOW_CONTRIBUTORS")
@SequenceGenerator(name = "idGenerator", sequenceName = "TV_SHOW_CONTRIBUTORS_SEQ", initialValue = 1, allocationSize
		= 1)
public class TVShowContributor implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name = "tv_show_id", referencedColumnName = "id")
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
