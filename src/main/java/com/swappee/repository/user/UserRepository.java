package com.swappee.repository.user;

import com.swappee.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the user entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
