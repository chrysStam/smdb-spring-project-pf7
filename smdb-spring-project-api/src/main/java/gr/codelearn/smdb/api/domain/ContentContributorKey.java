package gr.codelearn.smdb.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ContentContributorKey implements Serializable {
	@Column(name = "content_id")
	private Long contentId;
	@Column(name = "person_id")
	private Long personId;

	private Role role;
}
