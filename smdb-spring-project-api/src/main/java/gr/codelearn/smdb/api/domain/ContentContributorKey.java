package gr.codelearn.smdb.api.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
@Embeddable
public class ContentContributorKey implements Serializable {
	@Column(name = "content_id")
	private Long contentId;
	@Column(name = "person_id")
	private Long personId;
	@Enumerated(EnumType.STRING)
	private Role role;
}
