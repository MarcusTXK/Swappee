package com.swappee.dao.user;

import com.swappee.domain.user.User;
import com.swappee.utils.exception.BaseDaoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Data Access interface for managing users.
 */
public interface UserDao {
    User findById(Long id) throws BaseDaoException;

    User findByUsername(String username) throws BaseDaoException;

    List<User> getAll(List<Long> ids) throws BaseDaoException;

    Page<User> getAll(Pageable pageable) throws BaseDaoException;

    User create(User toCreate) throws BaseDaoException;

    User update(User toUpdate) throws BaseDaoException;

    User delete(User toDelete) throws BaseDaoException;

    User findUserByResetToken(String resetToken) throws BaseDaoException;

    User findByEmail(String email) throws BaseDaoException;

    User findByResetToken(String token) throws BaseDaoException;
}
