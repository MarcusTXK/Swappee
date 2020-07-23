package com.swappee.dao.review;

import com.swappee.domain.review.Review;
import com.swappee.utils.exception.BaseDaoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Data Access interface for managing reviews.
 */
public interface ReviewDao {
    Review findById(Long id) throws BaseDaoException;

    Page<Review> getAll(Pageable pageable) throws BaseDaoException;

    Review create(Review toCreate) throws BaseDaoException;

    Review update(Review toUpdate) throws BaseDaoException;

    Review delete(Review toDelete) throws BaseDaoException;
}
