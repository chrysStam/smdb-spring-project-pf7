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
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TV_SHOWS")
@SequenceGenerator(name = "idGenerator", sequenceName = "TV_SHOWS_SEQ", initialValue = 1, allocationSize = 1)
public class TVShow extends BaseModel {
	@NotNull(message = "TV Show's title cannot be null")
	@Column(length = 255, nullable = false)
	private String title;
	@NotNull
	@Column(name="start_year")
	private Integer startYear;
	private Integer endYear;
	private Double rating;

	@Column(name="num_seasons")
	private Byte numSeasons;

	@Column(name="plot_summary", columnDefinition = "LONGVARCHAR")
	private String plotSummary;
	private String country;

	@ManyToMany
	@JoinTable(
			name = "TV_SHOW_GENRE",
			joinColumns = { @JoinColumn(name = "tv_show_id") },
			inverseJoinColumns = { @JoinColumn(name = "genre_id") }
	)
	private Set<Genre> genres;

	@OneToMany(mappedBy = "tvShow", targetEntity = TVShowContributor.class)
	private Set<TVShowContributor> tvShowContributors;
}
