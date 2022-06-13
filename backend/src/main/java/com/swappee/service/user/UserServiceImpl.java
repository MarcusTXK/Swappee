package com.swappee.service.user;

import com.google.common.base.Preconditions;
import com.swappee.dao.request.RequestDao;
import com.swappee.dao.review.ReviewDao;
import com.swappee.dao.user.UserDao;
import com.swappee.domain.request.Request;
import com.swappee.domain.user.User;
import com.swappee.mapper.review.ReviewDTOMapper;
import com.swappee.mapper.user.UserDTOMapper;
import com.swappee.model.item.ItemDTO;
import com.swappee.model.review.ReviewDTO;
import com.swappee.model.user.UserDTO;
import com.swappee.model.user.UserViewDTO;
import com.swappee.service.item.ItemService;
import com.swappee.utils.exception.BaseDaoException;
import com.swappee.utils.exception.BaseServiceException;
import com.swappee.utils.exception.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation class for managing users.
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserDao userDao;

    @Autowired
    ItemService itemService;

    @Autowired
    ReviewDao reviewDao;

    @Autowired
    RequestDao requestDao;

    @Autowired
    ReviewDTOMapper reviewDTOMapper;

    @Autowired
    UserDTOMapper userDTOMapper;

    /**
     * Find user by id
     * For users when trying to get information on their own profile
     *
     * @param id
     * @return UserDTO
     * @throws BaseServiceException
     */
    @Override
    public UserDTO findUserById(Long id) throws BaseServiceException {
        try {
            logger.info("Start findUserById - id: {}", id);
            Preconditions.checkNotNull(id);
            UserDTO userDTO = userDTOMapper.mapEntity(userDao.findById(id));
            Preconditions.checkNotNull(userDTO);
            return userDTO;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.USER_ERROR_GET_ONE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findUserById");
        }
    }

    /**
     * Find user view by id
     * For users trying to get information on other users
     *
     * @param id
     * @return UserViewDTO
     * @throws BaseServiceException
     */
    @Override
    public UserViewDTO findUserViewById(Long id) throws BaseServiceException {
        try {
            logger.info("Start findUserById - id: {}", id);
            Preconditions.checkNotNull(id);
            return userViewDTOMapper(userDao.findById(id));
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.USER_ERROR_GET_ONE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findUserById");
        }
    }

    /**
     * Find user view by username
     * For users trying to get information on other users
     * @param username
     * @return
     * @throws BaseServiceException
     */

    @Override
    public UserViewDTO findUserViewByUsername(String username) throws BaseServiceException {
        try {
            logger.info("Start findByUsername - username: {}", username);
            Preconditions.checkNotNull(username);
            return userViewDTOMapper(userDao.findByUsername(username));
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.USER_ERROR_GET_ONE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findByUsername");
        }
    }

    /**
     * Find user by username
     *
     * @param username
     * @return userDTO
     * @throws BaseServiceException
     */
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

    /**
     * Find user views by id list
     *
     * @param ids
     * @return List<UserViewDTO>
     * @throws BaseServiceException
     */
    @Override
    public List<UserViewDTO> getAll(List<Long> ids) throws BaseServiceException {
        try {
            logger.info("Start getAll - ids: {}", ids);
            Preconditions.checkNotNull(ids);
            List<User> userList = userDao.getAll(ids);
            List<UserViewDTO> userViewDTOList = new ArrayList<>();
            for (User user : userList) {
                userViewDTOList.add(userViewDTOMapper(user));
            }
            return userViewDTOList;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.USER_ERROR_GET_LIST_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End getAll");
        }
    }

    /**
     * Find user views by page
     *
     * @param pageable
     * @return Page<UserViewDTO>
     * @throws BaseServiceException
     */
    @Override
    public Page<UserViewDTO> getAll(Pageable pageable) throws BaseServiceException {
        try {
            logger.info("Start getAll - pageable: {}", pageable);
            Preconditions.checkNotNull(pageable);
            return userDao.getAll(pageable).map(this::userViewDTOMapper);
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.USER_ERROR_GET_PAGE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End getAll");
        }
    }

    /**
     * Create user after encoding their password
     *
     * @param toCreate
     * @return created UserDTO
     * @throws BaseServiceException
     */
    @Override
    @Transactional(rollbackFor = {BaseServiceException.class})
    public UserDTO create(UserDTO toCreate) throws BaseServiceException {
        try {
            logger.info("Start create - toCreate: {}", toCreate);
            Preconditions.checkNotNull(toCreate);
            toCreate.setPassword(new BCryptPasswordEncoder().encode(toCreate.getPassword()));
            UserDTO userDTO = userDTOMapper.mapEntity(userDao.create(userDTOMapper.mapDto(toCreate)));
            Preconditions.checkNotNull(userDTO);
            return userDTO;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.USER_ERROR_CREATE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End create");
        }
    }

    /**
     * Create review and updates user
     * @param reviewDTO
     * @return true if successful, false if unsuccessful
     */

    @Override
    @Transactional(rollbackFor = {BaseServiceException.class})
    public Boolean reviewUser(ReviewDTO reviewDTO) throws BaseServiceException {
        try {
            logger.info("Start reviewUser - reviewDTO: {}", reviewDTO);
            Preconditions.checkNotNull(reviewDTO);
            //Check user to be reviewed exists
            User userReviewed = userDao.findById(reviewDTO.getReviewedId());
            Preconditions.checkNotNull(userReviewed);
            //Check user reviewing exists
            User userReviewer = userDao.findById(reviewDTO.getReviewerId());
            Preconditions.checkNotNull(userReviewer);
            //Check if request exists and if status is TRADED
            Request request = requestDao.findById(reviewDTO.getRequestId());
            Preconditions.checkNotNull(request);
            if (request.getStatus() == Request.Status.TRADED) {
                //Check if user reviewing is the owner or trader
                if (request.getOwnerId().equals(reviewDTO.getReviewerId())) {
                    if (request.isOwnerReviewed()) {
                        logger.warn("Reviewer has already reviewed - reviewerId: {}", reviewDTO.getReviewerId());
                        return false;
                    }
                    request.setOwnerReviewed(true);
                } else if (request.getTraderId().equals(reviewDTO.getReviewerId())) {
                    if (request.isTraderReviewed()) {
                        logger.warn("Reviewer has already reviewed - reviewerId: {}", reviewDTO.getReviewerId());
                        return false;
                    }
                    request.setTraderReviewed(true);
                } else {
                    logger.warn("Reviewer is neither owner nor trader of request - reviewerId: {}", reviewDTO.getReviewerId());
                    return false;
                }
                reviewDao.create(reviewDTOMapper.mapDto(reviewDTO));
                userReviewed.setScore(userReviewed.getScore() + reviewDTO.getScore());
                userReviewed.setTotalTraded(userReviewed.getTotalTraded() + 1);
                userDao.update(userReviewed);
                requestDao.update(request);
                return true;
            } else {
                logger.warn("Request status is not TRADED - requestId: {}", reviewDTO.getRequestId());
                return false;
            }
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.USER_ERROR_REVIEW_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End reviewUser");
        }
    }

    /**
     * Update User
     *
     * @param toUpdate
     * @return updated User
     * @throws BaseServiceException
     */
    @Override
    @Transactional(rollbackFor = {BaseServiceException.class})
    public UserDTO update(UserDTO toUpdate) throws BaseServiceException {
        try {
            logger.info("Start update service- toUpdate: {}", toUpdate);
            Preconditions.checkNotNull(toUpdate);
            //check if a new password was set, and encode it
            if(!toUpdate.getPassword().isEmpty()) {
                toUpdate.setPassword(new BCryptPasswordEncoder().encode(toUpdate.getPassword()));
            }
            UserDTO userDTO = userDTOMapper.mapEntity(userDao.update(userDTOMapper.mapDto(toUpdate)));
            Preconditions.checkNotNull(userDTO);
            return userDTO;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.USER_ERROR_UPDATE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End update service");
        }
    }

    /**
     * Delete User
     * Deletes Items under user as well
     * Note: soft deletes user and items
     *
     * @param toDelete
     * @return deleted user
     * @throws BaseServiceException
     */
    @Override
    @Transactional(rollbackFor = {BaseServiceException.class})
    public UserDTO delete(UserDTO toDelete) throws BaseServiceException {
        try {
            logger.info("Start delete - toDelete: {}", toDelete);
            Preconditions.checkNotNull(toDelete);
            UserDTO userDTO = userDTOMapper.mapEntity(userDao.delete(userDTOMapper.mapDto(toDelete)));
            Preconditions.checkNotNull(userDTO);
            //getAll items under user and delete each one
            List<ItemDTO> itemDTOList = itemService.findByUserId(userDTO.getId());
            for (ItemDTO itemDTO : itemDTOList) {
                itemService.delete(itemDTO);
            }
            return userDTO;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.USER_ERROR_DELETE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End delete");
        }
    }

    /**
     * Find user by email address
     * For password reset
     *
     * @param emailAddress
     * @return UserDTO
     * @throws BaseServiceException
     */
    @Override
    public UserDTO findByEmail(String emailAddress) throws BaseServiceException {
        try {
            logger.info("Start findByEmail - email: {}", emailAddress);
            Preconditions.checkNotNull(emailAddress);
            UserDTO userDTO = userDTOMapper.mapEntity(userDao.findByEmail(emailAddress));
            Preconditions.checkNotNull(userDTO);
            return userDTO;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.USER_ERROR_GET_ONE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findByEmail");
        }
    }

    @Override
    public UserDTO findByResetToken(String token) throws BaseServiceException {
        try {
            logger.info("Start findByResetToken - token: {}", token);
            Preconditions.checkNotNull(token);
            UserDTO userDTO = userDTOMapper.mapEntity(userDao.findByResetToken(token));
            Preconditions.checkNotNull(userDTO);
            return userDTO;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.USER_ERROR_GET_ONE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findByResetToken");
        }
    }

    //mapper to map User to UserViewDTO
    private UserViewDTO userViewDTOMapper(User user) {
        Preconditions.checkNotNull(user);

        UserViewDTO userViewDTO = new UserViewDTO();
        userViewDTO.setId(user.getId());
        userViewDTO.setUsername(user.getUsername());
        userViewDTO.setAvatarPath(user.getId().toString());
        userViewDTO.setEmailOnly(user.isEmailOnly());
        userViewDTO.setScore(user.getScore());
        userViewDTO.setTotalTraded(user.getTotalTraded());
        userViewDTO.setCreatedDate(user.getCreatedDate());
        userViewDTO.setLastModifiedDate(user.getLastModifiedDate());

        return userViewDTO;
    }
}
