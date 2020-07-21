package com.swappee.repository.likes;

import com.swappee.domain.likes.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the likes entity.
 */
@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
}
