package com.swappee.repository.like;

import com.swappee.domain.like.Like;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the like entity.
 */
@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findByUserIdAndItemId(Long userId, Long itemId);

    List<Like> findByUserId(Long userId, Pageable pageable);
}
