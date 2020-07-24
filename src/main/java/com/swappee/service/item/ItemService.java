package com.swappee.service.item;

import com.swappee.model.item.ItemCardDTO;
import com.swappee.model.item.ItemDTO;
import com.swappee.model.item.ItemViewDTO;
import com.swappee.model.picture.PictureDTO;
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

    List<ItemCardDTO> getAll(List<Long> ids) throws BaseServiceException;

    Page<ItemCardDTO> getAll(Pageable pageable) throws BaseServiceException;

    List<ItemDTO> findByUserId(Long userId) throws BaseServiceException;

    Page<ItemCardDTO> findByUserId(Long userId, Pageable pageable) throws BaseServiceException;

    ItemDTO create(ItemDTO toCreate, List<PictureDTO> pictureDTOList) throws BaseServiceException;

    ItemDTO update(ItemDTO toUpdate, List<PictureDTO> pictureDTOList) throws BaseServiceException;

    ItemDTO delete(ItemDTO toDelete) throws BaseServiceException;
}
