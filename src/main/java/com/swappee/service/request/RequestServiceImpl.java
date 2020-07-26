package com.swappee.service.request;

import com.swappee.model.request.RequestDTO;
import com.swappee.model.wrapper.GridResult;
import com.swappee.utils.exception.BaseDaoException;

public class RequestServiceImpl implements RequestService {
    @Override
    public GridResult findByOwnerId(Long ownerId) throws BaseDaoException {
        return null;
    }

    @Override
    public GridResult findByTraderId(Long traderId) throws BaseDaoException {
        return null;
    }

    @Override
    public RequestDTO create(RequestDTO toCreate) throws BaseDaoException {
        return null;
    }

    @Override
    public RequestDTO update(RequestDTO toUpdate) throws BaseDaoException {
        return null;
    }

    @Override
    public RequestDTO hide(Long requestId, Long userId) throws BaseDaoException {
        return null;
    }
}
