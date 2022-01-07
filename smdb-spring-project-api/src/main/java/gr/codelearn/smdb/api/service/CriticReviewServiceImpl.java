package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.CriticReview;
import gr.codelearn.smdb.api.repository.CriticReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriticReviewServiceImpl extends BaseServiceImpl<CriticReview> implements CriticReviewService {
	private final CriticReviewRepository criticReviewRepository;

	@Override
	public JpaRepository<CriticReview, Long> getRepository() {
		return criticReviewRepository;
	}

}
