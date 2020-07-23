package com.swappee.service.user;

import com.google.common.base.Preconditions;
import com.swappee.dao.user.UserDao;
import com.swappee.mapper.user.UserDTOMapper;
import com.swappee.model.user.UserDTO;
import com.swappee.utils.exception.BaseDaoException;
import com.swappee.utils.exception.BaseServiceException;
import com.swappee.utils.exception.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation class for managing users.
 */
@Service
public class UserServiceImpl implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserDao userDao;

    @Autowired
    UserDTOMapper userDTOMapper;

    @Override
    public UserDTO findById(Long id) throws BaseServiceException {
        try {
            logger.info("Start findById - id: {}", id);
            Preconditions.checkNotNull(id);
            UserDTO userDTO = userDTOMapper.mapEntity(userDao.findById(id));
            Preconditions.checkNotNull(userDTO);
            return userDTO;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.USER_ERROR_GET_ONE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findById");
        }
    }

    @Override
    public UserDTO findByUsername(String username) throws BaseServiceException {
        try {
            logger.info("Start findByUsername - username: {}", username);
            Preconditions.checkNotNull(username);
            UserDTO userDTO = userDTOMapper.mapEntity(userDao.findByUsername(username));
            Preconditions.checkNotNull(userDTO);
            return userDTO;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.USER_ERROR_GET_ONE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findByUsername");
        }
    }

    @Override
    public List<UserDTO> getAll(List<Long> ids) {
        return null;
    }

    @Override
    public Page<UserDTO> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public UserDTO create(UserDTO toCreate) {
        return null;
    }

    @Override
    public UserDTO update(UserDTO toUpdate) {
        return null;
    }

    @Override
    public UserDTO delete(UserDTO toDelete) {
        return null;
    }
}
