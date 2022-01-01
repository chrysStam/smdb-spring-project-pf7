package gr.codelearn.smdb.api.domain;

import gr.codelearn.smdb.api.converter.MotionPictureRatingConverter;
import gr.codelearn.smdb.api.converter.RoleConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
//	@Enumerated(EnumType.STRING)	// Not needed since we utilize a custom converter for Role
	private Role role;
}
