package com.swappee.service.user;

import com.swappee.model.review.ReviewDTO;
import com.swappee.model.user.UserDTO;
import com.swappee.model.user.UserViewDTO;
import com.swappee.utils.exception.BaseServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for managing users.
 */
public interface UserService {
    UserDTO findUserById(Long id) throws BaseServiceException;

    UserViewDTO findUserViewById(Long id) throws BaseServiceException;

    UserDTO findByUsername(String username) throws BaseServiceException;

    List<UserViewDTO> getAll(List<Long> ids) throws BaseServiceException;

    Page<UserViewDTO> getAll(Pageable pageable) throws BaseServiceException;

    UserDTO create(UserDTO toCreate) throws BaseServiceException;

    Boolean reviewUser(ReviewDTO reviewDTO) throws BaseServiceException;

    UserDTO update(UserDTO toUpdate) throws BaseServiceException;

    UserDTO delete(UserDTO toDelete) throws BaseServiceException;
}
