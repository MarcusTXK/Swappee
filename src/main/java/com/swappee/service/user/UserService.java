package com.swappee.service.user;

import com.swappee.model.user.UserDTO;
import com.swappee.utils.exception.BaseServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for managing users.
 */
public interface UserService {
    UserDTO findById(Long id) throws BaseServiceException;

    UserDTO findByUsername(String username) throws BaseServiceException;

    List<UserDTO> getAll(List<Long> ids);

    Page<UserDTO> getAll(Pageable pageable);

    UserDTO create(UserDTO toCreate);

    UserDTO update(UserDTO toUpdate);

    UserDTO delete(UserDTO toDelete);
}
