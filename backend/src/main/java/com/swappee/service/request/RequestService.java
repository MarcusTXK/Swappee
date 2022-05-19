package com.swappee.service.request;

import com.swappee.domain.request.Request;
import com.swappee.model.request.RequestDTO;
import com.swappee.model.wrapper.GridResult;
import com.swappee.utils.exception.BaseServiceException;
import org.springframework.data.domain.Pageable;

public interface RequestService {

//    RequestDTO findById(Long id) throws BaseServiceException;

    GridResult findByOwnerId(Long ownerId, Pageable pageable) throws BaseServiceException;

    GridResult findByTraderId(Long traderId, Pageable pageable) throws BaseServiceException;

    RequestDTO create(RequestDTO toCreate) throws BaseServiceException;

    RequestDTO updateStatus(Long requestId, Request.Status status) throws BaseServiceException;

    RequestDTO update(RequestDTO toUpdate) throws BaseServiceException;

    RequestDTO hide(Long requestId, Long userId) throws BaseServiceException;

    RequestDTO findById(Long requestId) throws BaseServiceException;
}
