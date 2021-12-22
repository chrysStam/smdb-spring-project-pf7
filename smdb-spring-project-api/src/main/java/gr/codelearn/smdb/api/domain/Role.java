package gr.codelearn.smdb.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name ="ROLES")
@SequenceGenerator(name = "idGenerator", sequenceName = "ROLES_SEQ", initialValue = 1, allocationSize = 1)
public class Role extends BaseModel {
	@NotNull
	private String name;

	@OneToMany(mappedBy = "role")
	private Set<FilmContributor> filmContributorRoles;

}
