package com.swappee.service.item;

import com.swappee.model.item.ItemCardDTO;
import com.swappee.model.item.ItemDTO;
import com.swappee.model.item.ItemViewDTO;
import com.swappee.utils.exception.BaseServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for managing items.
 */
public interface ItemService {
    ItemDTO findItemById(Long id) throws BaseServiceException;

    ItemViewDTO findItemViewById(Long id) throws BaseServiceException;

    List<ItemCardDTO> getAll(List<Long> ids);

    Page<ItemCardDTO> getAll(Pageable pageable);

    List<ItemDTO> findByUserId(Long userId);

    Page<ItemCardDTO> findByUserId(Long userId, Pageable pageable);

    ItemDTO create(ItemDTO toCreate);

    ItemDTO update(ItemDTO toUpdate);

    ItemDTO delete(ItemDTO toDelete);
}
