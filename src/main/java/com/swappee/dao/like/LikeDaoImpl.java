package com.swappee.dao.like;

import com.google.common.base.Preconditions;
import com.swappee.domain.like.Like;
import com.swappee.repository.like.LikeRepository;
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

/**
 * An Data Access Object implementation for managing likes.
 */
@Component
public class LikeDaoImpl implements LikeDao {
    private static final Logger logger = LoggerFactory.getLogger(LikeDaoImpl.class);

    @Autowired
    LikeRepository likeRepository;

    @Override
    public Like findById(Long id) throws BaseDaoException {
        logger.info("Start findById - id: {}", id);
        try {
            Preconditions.checkNotNull(id);
            return likeRepository.findById(id).orElse(null);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_ONE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findById");
        }
    }

    @Override
    public Like findByUserIdAndItemId(Long userId, Long itemId) throws BaseDaoException {
        logger.info("Start findByUserIdAndItemId - userId: {}, itemId: {}", userId, itemId);
        try {
            Preconditions.checkNotNull(userId);
            Preconditions.checkNotNull(itemId);
            return likeRepository.findByUserIdAndItemId(userId, itemId).orElse(null);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_ONE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findByUserIdAndItemId");
        }
    }

    @Override
    public Page<Like> findByUserId(Long userId, Pageable pageable) throws BaseDaoException {
        logger.info("Start findByUserId - userId: {}.  pageable: {}", userId, pageable);
        try {
            Preconditions.checkNotNull(pageable);
            return likeRepository.findByUserId(userId, pageable);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_PAGE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findByUserId");
        }
    }

    @Override
    @Transactional
    @Lock(LockModeType.OPTIMISTIC)
    public Like create(Like toCreate) throws BaseDaoException {
        logger.info("Start create - toCreate: {}", toCreate);
        try {
            Preconditions.checkNotNull(toCreate);
            return this.likeRepository.save(toCreate);
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
    public Like update(Like toUpdate) throws BaseDaoException {
        logger.info("Start update - toUpdate: {}", toUpdate);
        try {
            Preconditions.checkNotNull(toUpdate);
            return this.likeRepository.save(toUpdate);
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
    public void delete(Like toDelete) throws BaseDaoException {
        logger.info("Start delete - toDelete: {}", toDelete);
        try {
            Preconditions.checkNotNull(toDelete);
            Preconditions.checkNotNull(toDelete.getId());
            this.likeRepository.deleteById(toDelete.getId());
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_DELETE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End delete");
        }
    }
}
