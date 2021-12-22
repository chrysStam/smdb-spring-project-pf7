package gr.codelearn.smdb.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "FILMS")
@SequenceGenerator(name = "idGenerator", sequenceName = "FILMS_SEQ", initialValue = 1, allocationSize = 1)
public class Film extends BaseModel {

	@NotNull(message = "Film's title cannot be null")
	@Column(length = 255, nullable = false)
	private String title;
	private Integer duration;
	private Double rating;

	@NotNull
	@Column(name="release_year")
	private Integer releaseYear;

	private String language;
	private BigInteger budget;

	@Column(name="plot_summary", columnDefinition = "LONGVARCHAR")
	private String plotSummary;

	@ManyToMany
	@JoinTable(
			name = "FILM_GENRE",
			joinColumns = { @JoinColumn(name = "film_id") },
			inverseJoinColumns = { @JoinColumn(name = "genre_id") }
	)
	private Set<Genre> genres;

	@OneToMany(mappedBy = "film", targetEntity = FilmContributor.class)
	private Set<FilmContributor> filmContributors;
}
