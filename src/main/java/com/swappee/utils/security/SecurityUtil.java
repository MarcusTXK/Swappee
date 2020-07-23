package com.swappee.utils.security;

import com.swappee.model.user.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * A utility class to get current user from spring security context
 */
@Component
public class SecurityUtil {
    private final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    /**
     * Returns the username used to authenticate the user.
     *
     * @return the username
     */
    public String getAuthenticatedUsername() {
        String username = null;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDTO) principal).getUsername();
        } else {
            username = principal.toString();
        }
        logger.debug("Current username is {}", username);
        return username;
    }

    /**
     * Returns the user identified related to authenticated user.
     *
     * @return userId
     */
    public long getAuthenticatedUserId() {
        Long userId = null;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userId = ((UserDTO) principal).getId();
        }

        logger.debug("Current userid is {}", userId);
        return userId;
    }

}
