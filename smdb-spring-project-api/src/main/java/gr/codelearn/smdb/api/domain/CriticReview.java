package gr.codelearn.smdb.api.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CRITIC_REVIEWS")
@SequenceGenerator(name = "idGenerator", sequenceName = "CRITIC_REVIEWS_SEQ", initialValue = 1, allocationSize = 1)
public class CriticReview extends BaseModel {
	@NotNull
	@Column(length = 50)
	private String author;

	@Column(length = 4096)
	private String body;

	@NotNull
	@Temporal(TemporalType.DATE)	// YYYY-MM-DD
	@Column(name="submitted_at")
	private Date submittedAt;

	@NotNull
	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "10.0", inclusive = true)
	@Digits(integer = 2, fraction = 1)
	private Double rating;

	@JsonBackReference("criticReviews")
	@ManyToOne
	@JoinColumn(name = "content_id")
	private Content content;
}
