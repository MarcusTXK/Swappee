package com.swappee.repository.users;

import com.swappee.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the users entity.
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
