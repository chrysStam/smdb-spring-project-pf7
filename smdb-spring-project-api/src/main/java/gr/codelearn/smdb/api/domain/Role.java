package gr.codelearn.smdb.api.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name ="ROLES")
@SequenceGenerator(name = "idGenerator", sequenceName = "ROLES_SEQ", initialValue = 1, allocationSize = 1)
public class Role extends BaseModel {
	String name;

	@OneToMany(mappedBy = "role")
	private Set<FilmContributor> filmContributorRoles;

}
