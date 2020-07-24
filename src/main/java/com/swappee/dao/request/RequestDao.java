package com.swappee.dao.request;

import com.swappee.domain.request.Request;
import com.swappee.utils.exception.BaseDaoException;

import java.util.List;

/**
 * Data Access interface for managing requests.
 */
public interface RequestDao {
    Request findById(Long id) throws BaseDaoException;

    List<Request> findByOwnerId(Long ownerId) throws BaseDaoException;

    List<Request> findByTraderId(Long traderId) throws BaseDaoException;

    Request create(Request toCreate) throws BaseDaoException;

    Request update(Request toUpdate) throws BaseDaoException;

    Request delete(Request toDelete) throws BaseDaoException;
}
