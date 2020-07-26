package com.swappee.dao.request;

import com.swappee.domain.request.Request;
import com.swappee.utils.exception.BaseDaoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Data Access interface for managing requests.
 */
public interface RequestDao {
    Request findById(Long id) throws BaseDaoException;

    Page<Request> findByOwnerIdAndOwnerHiddenFalse(Long ownerId, Pageable pageable) throws BaseDaoException;

    Page<Request> findByTraderIdAndTraderHiddenFalse(Long traderId, Pageable pageable) throws BaseDaoException;

    List<Request> findByOwnerItemId(Long ownerItemId) throws BaseDaoException;

    List<Request> findByTraderItemId(Long traderItemId) throws BaseDaoException;

    Request create(Request toCreate) throws BaseDaoException;

    Request update(Request toUpdate) throws BaseDaoException;

    Request delete(Request toDelete) throws BaseDaoException;
}
