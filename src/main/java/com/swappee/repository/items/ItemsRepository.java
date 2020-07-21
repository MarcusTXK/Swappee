package com.swappee.repository.items;

import com.swappee.domain.items.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the items entity.
 */
@Repository
public interface ItemsRepository extends JpaRepository<Items, Long> {
}
