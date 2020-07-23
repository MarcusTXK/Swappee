package com.swappee.repository.picture;

import com.swappee.domain.picture.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the picture entity.
 */
@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
    long deleteByItemId(Long itemId);

    Optional<Picture> findByItemIdAndOrderIs(Long itemId, Long order);

    List<Picture> findByItemId(Long itemId);
}
