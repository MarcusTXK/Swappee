package com.swappee.repository.like;

import com.swappee.domain.like.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the like entity.
 */
@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

}
