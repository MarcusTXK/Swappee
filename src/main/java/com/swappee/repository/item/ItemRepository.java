package com.swappee.repository.item;

import com.swappee.domain.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the item entity.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByUserId(Long userId);

    Page<Item> findByUserId(Long userId, Pageable pageable);

    Page<Item> findByCategory(String category, Pageable pageable);
}
