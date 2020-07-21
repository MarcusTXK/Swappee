package com.swappee.repository.requests;

import com.swappee.domain.pictures.Pictures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the pictures entity.
 */
@Repository
public interface RequestsRepository extends JpaRepository<Pictures, Long> {
}
