package com.swappee.dao.request;

import com.google.common.base.Preconditions;
import com.swappee.domain.request.Request;
import com.swappee.repository.request.RequestRepository;
import com.swappee.utils.exception.BaseDaoException;
import com.swappee.utils.exception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * An Data Access Object implementation for managing requests.
 */
@Component
public class RequestDaoImpl implements RequestDao {
    private static final Logger logger = LoggerFactory.getLogger(RequestDaoImpl.class);

    @Autowired
    RequestRepository requestRepository;

    @Override
    public Request findById(Long id) throws BaseDaoException {
        logger.info("Start findById - id: {}", id);
        try {
            Preconditions.checkNotNull(id);
            return requestRepository.findById(id).orElse(null);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_ONE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findById");
        }
    }

    @Override
    public Page<Request> findByOwnerIdAndOwnerHiddenFalse(Long ownerId, Pageable pageable) throws BaseDaoException {
        logger.info("Start findByOwnerIdAndHiddenFalse - ownerId: {}, pageable: {}", ownerId, pageable);
        try {
            Preconditions.checkNotNull(ownerId);
            return this.requestRepository.findByOwnerIdAndOwnerHiddenFalse(ownerId, pageable);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_PAGE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findByOwnerIdAndHiddenFalse");
        }
    }

    @Override
    public Page<Request> findByTraderIdAndTraderHiddenFalse(Long traderId, Pageable pageable) throws BaseDaoException {
        logger.info("Start findByTraderIdAndTraderHiddenFalse - traderId: {}, pageable: {}", traderId, pageable);
        try {
            Preconditions.checkNotNull(traderId);
            return this.requestRepository.findByTraderIdAndTraderHiddenFalse(traderId, pageable);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_PAGE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findByTraderIdAndTraderHiddenFalse");
        }
    }

    @Override
    public List<Request> findByOwnerItemId(Long ownerItemId) throws BaseDaoException {
        logger.info("Start findByOwnerItemId - ownerItemId: {}", ownerItemId);
        try {
            Preconditions.checkNotNull(ownerItemId);
            return this.requestRepository.findByOwnerItemId(ownerItemId);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_LIST_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findByOwnerItemId");
        }
    }

    @Override
    public List<Request> findByTraderItemId(Long traderItemId) throws BaseDaoException {
        logger.info("Start findByTraderItemId - traderItemId: {}", traderItemId);
        try {
            Preconditions.checkNotNull(traderItemId);
            return this.requestRepository.findByTraderItemId(traderItemId);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_LIST_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findByTraderItemId");
        }
    }

    @Override
    @Transactional
    @Lock(LockModeType.OPTIMISTIC)
    public Request create(Request toCreate) throws BaseDaoException {
        logger.info("Start create - toCreate: {}", toCreate);
        try {
            Preconditions.checkNotNull(toCreate);
            return this.requestRepository.save(toCreate);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_CREATE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End create");
        }
    }

    @Override
    @Transactional
    @Lock(LockModeType.OPTIMISTIC)
    public Request update(Request toUpdate) throws BaseDaoException {
        logger.info("Start update - toUpdate: {}", toUpdate);
        try {
            Preconditions.checkNotNull(toUpdate);
            return this.requestRepository.save(toUpdate);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_UPDATE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End update");
        }
    }

    @Override
    @Transactional
    @Lock(LockModeType.OPTIMISTIC)
    public Request delete(Request toDelete) throws BaseDaoException {
        logger.info("Start delete - toDelete: {}", toDelete);
        try {
            Preconditions.checkNotNull(toDelete);
            toDelete.setDeleted(true);
            return this.requestRepository.save(toDelete);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_DELETE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End delete");
        }
    }
}
