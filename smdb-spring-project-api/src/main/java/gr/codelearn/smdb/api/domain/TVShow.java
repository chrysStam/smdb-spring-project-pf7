package gr.codelearn.smdb.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "TV_SHOWS")
@SequenceGenerator(name = "idGenerator", sequenceName = "TV_SHOWS_SEQ", initialValue = 51, allocationSize = 1)
public class TVShow extends Content{
	@Column(name="end_year")
	private Integer endYear;

	@NotNull
	@Column(name = "num_seasons")
	private Integer numSeasons;
}
