package com.swappee.repository.like;

import com.swappee.domain.like.Like;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the like entity.
 */
@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserIdAndItemId(Long userId, Long itemId);

    List<Like> findByUserId(Long userId);

    Page<Like> findByUserId(Long userId, Pageable pageable);
}
