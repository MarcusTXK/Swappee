package com.swappee.dao.user;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.swappee.domain.user.User;
import com.swappee.repository.user.UserRepository;
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
 * An Data Access Object implementation for managing users.
 */
@Component
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public User findById(Long id) throws BaseDaoException {
        logger.info("Start findById - id: {}", id);
        try {
            Preconditions.checkNotNull(id);
            return userRepository.findById(id).orElse(null);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_ONE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findById");
        }
    }

    @Override
    public User findByUsername(String username) throws BaseDaoException {
        logger.info("Start findByUsername - username: {}", username);
        try {
            Preconditions.checkNotNull(username);
            return userRepository.findByUsername(username).orElse(null);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_ONE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findByUsername");
        }
    }

    @Override
    public List<User> getAll(List<Long> ids) throws BaseDaoException {
        logger.info("Start getAll list - ids: {}", ids);
        try {
            if (ids.isEmpty()) {
                return Lists.newArrayList();
            }
            return this.userRepository.findAllById(ids);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_LIST_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End getAll list by id");
        }
    }

    @Override
    public Page<User> getAll(Pageable pageable) throws BaseDaoException {
        logger.info("Start getAll page - pageable: {}", pageable);
        try {
            Preconditions.checkNotNull(pageable);
            return userRepository.findAll(pageable);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_PAGE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End getAll page");
        }
    }

    @Override
    @Transactional
    @Lock(LockModeType.OPTIMISTIC)
    public User create(User toCreate) throws BaseDaoException {
        logger.info("Start create - toCreate: {}", toCreate);
        try {
            Preconditions.checkNotNull(toCreate);
            return this.userRepository.save(toCreate);
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
    public User update(User toUpdate) throws BaseDaoException {
        logger.info("Start update - toUpdate: {}", toUpdate);
        try {
            Preconditions.checkNotNull(toUpdate);
            Preconditions.checkNotNull(toUpdate.getId());

            User originalEntity = userRepository.getOne(toUpdate.getId());
            originalEntity.setFirstName(toUpdate.getFirstName());
            originalEntity.setLastName(toUpdate.getLastName());
            originalEntity.setUsername(toUpdate.getUsername());
            if (!toUpdate.getPassword().isEmpty()) {
                originalEntity.setPassword(toUpdate.getPassword());
            }
            originalEntity.setEmail(toUpdate.getEmail());
            originalEntity.setAvatar(toUpdate.getAvatar());
            originalEntity.setPhone(toUpdate.getPhone());
            originalEntity.setEmailOnly(toUpdate.isEmailOnly());
            originalEntity.setRole(toUpdate.getRole());
            originalEntity.setScore(toUpdate.getScore());
            originalEntity.setTotalTraded(toUpdate.getTotalTraded());

            return this.userRepository.save(originalEntity);
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
    public User delete(User toDelete) throws BaseDaoException {
        logger.info("Start delete - toDelete: {}", toDelete);
        try {
            Preconditions.checkNotNull(toDelete);
            Preconditions.checkNotNull(toDelete.getId());

            User originalEntity = userRepository.getOne(toDelete.getId());
            originalEntity.setDeleted(true);
            return this.userRepository.save(originalEntity);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_DELETE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End delete");
        }
    }
}
