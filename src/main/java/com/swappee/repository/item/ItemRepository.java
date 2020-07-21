package com.swappee.repository.item;

import com.swappee.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the item entity.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
