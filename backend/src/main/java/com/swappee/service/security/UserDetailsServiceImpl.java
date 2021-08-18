package com.swappee.service.security;

import com.google.common.base.Preconditions;
import com.swappee.service.user.UserService;
import com.swappee.utils.exception.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service implementation class for Spring Security to manage users.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            logger.info("Start loadUserByUsername - username: {}", username);
            Preconditions.checkNotNull(username);
            return userService.findByUsername(username);
        } catch (Exception ex) {
            throw new UsernameNotFoundException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End loadUserByUsername");
        }
    }

}