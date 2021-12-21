package gr.codelearn.smdb.api.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TV_SHOWS")
@SequenceGenerator(name = "idGenerator", sequenceName = "TV_SHOWS_SEQ", initialValue = 1, allocationSize = 1)
public class TVShow extends BaseModel {
	private String title;
	private Date startYear;
	private Date endYear;
	private String country;
	private Byte numSeasons;
	private String plotSummary;
	private Double rating;

	@ManyToMany
	@JoinTable(
			name = "TV_SHOWS_GENRES",
			joinColumns = { @JoinColumn(name = "tv_show_id") },
			inverseJoinColumns = { @JoinColumn(name = "genre_id") }
	)
	private Set<Genre> genres;

	@OneToMany(mappedBy = "film")
	private Set<TVShowContributor> tvShowContributors;
}
