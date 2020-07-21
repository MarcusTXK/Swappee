package com.swappee.repository.reviews;

import com.swappee.domain.reviews.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the reviews entity.
 */
@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
}
