package com.swappee.repository.review;

import com.swappee.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the review entity.
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
