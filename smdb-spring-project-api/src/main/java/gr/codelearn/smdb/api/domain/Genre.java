package gr.codelearn.smdb.api.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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
@Table(name = "GENRES")
@SequenceGenerator(name = "idGenerator", sequenceName = "GENRES_SEQ", initialValue = 1, allocationSize = 1)
public class Genre extends BaseModel {
	@NotNull
	private String name;

	@ManyToMany(mappedBy = "genres")
	private Set<Film> films;

	@ManyToMany(mappedBy = "genres")
	private Set<TVShow> tvShows;
}
