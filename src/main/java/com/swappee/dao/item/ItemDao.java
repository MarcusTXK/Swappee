package com.swappee.dao.item;

import com.swappee.domain.item.Item;
import com.swappee.utils.exception.BaseDaoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Data Access interface for managing items.
 */
public interface ItemDao {
    Item findById(Long id) throws BaseDaoException;

    List<Item> getAll(List<Long> ids) throws BaseDaoException;

    Page<Item> getAll(Pageable pageable) throws BaseDaoException;

    Page<Item> findByCategory(String category, Pageable pageable) throws BaseDaoException;

    List<Item> findByUserId(Long userId) throws BaseDaoException;

    Page<Item> findByUserId(Long userId, Pageable pageable) throws BaseDaoException;

    Item create(Item toCreate) throws BaseDaoException;

    Item update(Item toUpdate) throws BaseDaoException;

    Item delete(Item toDelete) throws BaseDaoException;
}
