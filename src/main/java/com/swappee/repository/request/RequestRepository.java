package com.swappee.repository.request;

import com.swappee.domain.picture.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the request entity.
 */
@Repository
public interface RequestRepository extends JpaRepository<Picture, Long> {
}
