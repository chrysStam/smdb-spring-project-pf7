package gr.codelearn.smdb.api.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "FILMS")
@SequenceGenerator(name = "idGenerator", sequenceName = "FILMS_SEQ", initialValue = 1, allocationSize = 1)
public class Film extends BaseModel {
	private String title;
	private Date releaseYear;
	private Integer duration;
	private String language;
	private BigInteger budget;
	private String plotSummary;
	private Double rating;

	@ManyToMany
	@JoinTable(
			name = "FILM_GENRE",
			joinColumns = { @JoinColumn(name = "film_id") },
			inverseJoinColumns = { @JoinColumn(name = "genre_id") }
	)
	private Set<Genre> genres;

	@OneToMany(mappedBy = "film")
	private Set<FilmContributor> filmContributors;
}
