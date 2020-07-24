package com.swappee.dao.like;

import com.swappee.domain.like.Like;
import com.swappee.utils.exception.BaseDaoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Data Access interface for managing likes.
 */
public interface LikeDao {
    Like findById(Long id) throws BaseDaoException;

    Like findByUserIdAndItemId(Long userId, Long itemId) throws BaseDaoException;

    Page<Like> findByUserId(Long userId, Pageable pageable) throws BaseDaoException;

    List<Like> findByUserId(Long userId) throws BaseDaoException;

    Like create(Like toCreate) throws BaseDaoException;

    Like update(Like toUpdate) throws BaseDaoException;

    void delete(Like toDelete) throws BaseDaoException;
}
