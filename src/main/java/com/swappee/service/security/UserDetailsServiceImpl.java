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
    private final static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
//    static {
//        inMemoryUserList.add(new JwtUserDetails(1L, "in28minutes",
//                "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
//    }

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