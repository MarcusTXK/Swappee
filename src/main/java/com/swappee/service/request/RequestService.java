package com.swappee.service.request;

import com.swappee.model.request.RequestDTO;
import com.swappee.model.wrapper.GridResult;
import com.swappee.utils.exception.BaseDaoException;

public interface RequestService {

    GridResult findByOwnerId(Long ownerId) throws BaseDaoException;

    GridResult findByTraderId(Long traderId) throws BaseDaoException;

    RequestDTO create(RequestDTO toCreate) throws BaseDaoException;

    RequestDTO update(RequestDTO toUpdate) throws BaseDaoException;

    RequestDTO hide(Long requestId, Long userId) throws BaseDaoException;
}
