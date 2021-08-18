package com.swappee.utils.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * To allow Spring Data JPA to keep track of who is updating the database
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<String> createAuditorProvider() {
        return new SecurityAuditor();
    }

    public static class SecurityAuditor implements AuditorAware<String> {
        @Override
        public Optional<String> getCurrentAuditor() {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            return Optional.of(auth != null ? auth.getName() : "UknownUser");
        }
    }
}