package com.swappee.dao.picture;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.swappee.domain.picture.Picture;
import com.swappee.repository.picture.PictureRepository;
import com.swappee.utils.exception.BaseDaoException;
import com.swappee.utils.exception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * An Data Access Object implementation for managing pictures.
 */
public class PictureDaoImpl implements PictureDao{
    private static final Logger logger = LoggerFactory.getLogger(PictureDaoImpl.class);

    @Autowired
    PictureRepository pictureRepository;

    @Override
    public Picture findById(Long id) throws BaseDaoException {
        logger.info("Start findById - id: {}", id);
        try {
            return pictureRepository.findById(id).orElse(null);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_ONE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findById");
        }
    }

    @Override
    public Picture findByItemIdAndOrderIs0(Long itemId) throws BaseDaoException {
        logger.info("Start findByItemIdAndOrderIs0 - itemId: {}", itemId);
        try {
            return pictureRepository.findByItemIdAndOrderIs0(itemId).orElse(null);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_ONE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findByItemIdAndOrderIs0");
        }
    }

    @Override
    public List<Picture> findByItemId(Long itemId) throws BaseDaoException {
        logger.info("Start findByItemId - itemId: {}", itemId);
        try {
            Preconditions.checkArgument(itemId != null);
            return this.pictureRepository.findByItemId(itemId);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_LIST_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findByItemId");
        }
    }

    @Override
    @Transactional
    @Lock(LockModeType.OPTIMISTIC)
    public List<Picture> create(List<Picture> toCreates) throws BaseDaoException {
        logger.info("Start create list - toCreates : {}", toCreates);
        try {
            Preconditions.checkArgument(toCreates != null);
            if (toCreates.isEmpty()) {
                return Lists.newArrayList();
            }
            return this.pictureRepository.saveAll(toCreates);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_CREATE_LIST_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End create list");
        }
    }

    @Override
    @Transactional
    @Lock(LockModeType.OPTIMISTIC)
    public List<Picture> update(List<Picture> toUpdates) throws BaseDaoException {
        logger.info("Start update list - toUpdates : {}", toUpdates);
        try {
            Preconditions.checkArgument(toUpdates != null);
            if (toUpdates.isEmpty()) {
                return Lists.newArrayList();
            }
            long deleted = pictureRepository.deleteByItemId(toUpdates.get(0).getItemId());
            logger.info("deleted old pictures - no. of deleted : {}", deleted);
            return this.pictureRepository.saveAll(toUpdates);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_CREATE_LIST_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End update list");
        }
    }
}
