package com.swappee.repository.pictures;

import com.swappee.domain.pictures.Pictures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the pictures entity.
 */
@Repository
public interface PicturesRepository extends JpaRepository<Pictures, Long> {
}
