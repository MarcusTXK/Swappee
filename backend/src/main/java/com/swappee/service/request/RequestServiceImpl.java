package com.swappee.service.request;

import com.google.common.base.Preconditions;
import com.swappee.dao.request.RequestDao;
import com.swappee.domain.request.Request;
import com.swappee.mapper.request.RequestDTOMapper;
import com.swappee.model.request.RequestDTO;
import com.swappee.model.wrapper.GridResult;
import com.swappee.utils.exception.BaseDaoException;
import com.swappee.utils.exception.BaseServiceException;
import com.swappee.utils.exception.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation class for managing requests.
 * TODO Note: May need to set default date for last modified date
 */
@Service
public class RequestServiceImpl implements RequestService {
    private static final Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);

    @Autowired
    RequestDao requestDao;

    @Autowired
    RequestDTOMapper requestDTOMapper;

    /**
     * Get a page of requests by the owner's id
     *
     * @param ownerId
     * @return
     * @throws BaseDaoException
     */

    @Override
    public GridResult findByOwnerId(Long ownerId, Pageable pageable) throws BaseServiceException {
        try {
            logger.info("Start findByOwnerId - ownerId: {}, pageable: {}", ownerId, pageable);
            Preconditions.checkNotNull(ownerId);
            Preconditions.checkNotNull(pageable);

            Page<Request> requestPage = requestDao.findByOwnerIdAndOwnerHiddenFalse(ownerId, pageable);
            List<RequestDTO> requestDTOList = new ArrayList<>();
            for (Request request : requestPage) {
                requestDTOList.add(requestDTOMapper.mapEntity(request));
            }
            GridResult gridResult = new GridResult();
            gridResult.setData(requestDTOList);
            gridResult.setTotalRecordCount((int) requestPage.getTotalElements());
            gridResult.setIsSuccess(true);
            return gridResult;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.REQUEST_ERROR_GET_PAGE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findByOwnerId");
        }
    }

    /**
     * Get a page of requests by the trader's id
     *
     * @param traderId
     * @param pageable
     * @return
     * @throws BaseServiceException
     */

    @Override
    public GridResult findByTraderId(Long traderId, Pageable pageable) throws BaseServiceException {
        try {
            logger.info("Start findByTraderId - traderId: {}, pageable: {}", traderId, pageable);
            Preconditions.checkNotNull(traderId);
            Preconditions.checkNotNull(pageable);

            Page<Request> requestPage = requestDao.findByTraderIdAndTraderHiddenFalse(traderId, pageable);
            List<RequestDTO> requestDTOList = new ArrayList<>();
            for (Request request : requestPage) {
                requestDTOList.add(requestDTOMapper.mapEntity(request));
            }
            GridResult gridResult = new GridResult();
            gridResult.setData(requestDTOList);
            gridResult.setTotalRecordCount((int) requestPage.getTotalElements());
            gridResult.setIsSuccess(true);
            return gridResult;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.REQUEST_ERROR_GET_PAGE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End traderId");
        }
    }

    /**
     * Create RequestDTO
     *
     * @param toCreate
     * @return
     * @throws BaseServiceException
     */
    @Override
    @Transactional(rollbackFor = {BaseServiceException.class})
    public RequestDTO create(RequestDTO toCreate) throws BaseServiceException {
        try {
            logger.info("Start create - toCreate: {}", toCreate);
            Preconditions.checkNotNull(toCreate);

            RequestDTO requestDTO = requestDTOMapper.mapEntity(requestDao.create(requestDTOMapper.mapDto(toCreate)));
            Preconditions.checkNotNull(requestDTO);
            return requestDTO;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.REQUEST_ERROR_CREATE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End create");
        }
    }

    /**
     * Update status of RequestDTO
     *
     * @param requestId
     * @param status
     * @return
     */
    @Override
    @Transactional(rollbackFor = {BaseServiceException.class})
    public RequestDTO updateStatus(Long requestId, Request.Status status) throws BaseServiceException {
        try {
            logger.info("Start updateStatus - requestId: {}, status: {}", requestId, status);
            Preconditions.checkNotNull(requestId);
            Preconditions.checkNotNull(status);

            Request toUpdate = requestDao.findById(requestId);
            Preconditions.checkNotNull(toUpdate);
            toUpdate.setStatus(status);
            RequestDTO requestDTO = requestDTOMapper.mapEntity(requestDao.update(toUpdate));
            Preconditions.checkNotNull(requestDTO);
            return requestDTO;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.REQUEST_ERROR_UPDATE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End updateStatus");
        }
    }

    /**
     * Update RequestDTO
     *
     * @param toUpdate
     * @return
     * @throws BaseServiceException
     */
    @Override
    @Transactional(rollbackFor = {BaseServiceException.class})
    public RequestDTO update(RequestDTO toUpdate) throws BaseServiceException {
        try {
            logger.info("Start update - toUpdate: {}", toUpdate);
            Preconditions.checkNotNull(toUpdate);

            RequestDTO requestDTO = requestDTOMapper.mapEntity(requestDao.create(requestDTOMapper.mapDto(toUpdate)));
            Preconditions.checkNotNull(requestDTO);
            return requestDTO;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.REQUEST_ERROR_UPDATE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End update");
        }
    }

    /**
     * Hide request
     *
     * @param requestId
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = {BaseServiceException.class})
    public RequestDTO hide(Long requestId, Long userId) throws BaseServiceException {
        try {
            logger.info("Start hide - requestId: {}, userId: {}", requestId, userId);
            Preconditions.checkNotNull(requestId);
            Preconditions.checkNotNull(userId);

            Request toUpdate = requestDao.findById(requestId);
            Preconditions.checkNotNull(toUpdate);
            if (toUpdate.getOwnerId().equals(userId)) {
                toUpdate.setOwnerHidden(true);
            } else if (toUpdate.getTraderId().equals(userId)) {
                toUpdate.setTraderHidden(true);
            }
            RequestDTO requestDTO = requestDTOMapper.mapEntity(requestDao.update(toUpdate));
            Preconditions.checkNotNull(requestDTO);
            return requestDTO;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.REQUEST_ERROR_HIDE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End hide");
        }
    }
}
