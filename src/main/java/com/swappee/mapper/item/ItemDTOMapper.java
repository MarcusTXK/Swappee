package com.swappee.mapper.item;

import com.google.common.base.Preconditions;
import com.swappee.domain.item.Item;
import com.swappee.domain.item.ItemHistory;
import com.swappee.domain.item.PreferredItem;
import com.swappee.mapper.DTOMapper;
import com.swappee.model.item.ItemDTO;
import com.swappee.model.item.ItemHistoryDTO;
import com.swappee.model.item.PreferredItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemDTOMapper implements DTOMapper<ItemDTO, Item> {

    @Autowired
    ItemHistoryDTOMapper itemHistoryDTOMapper;

    @Autowired
    PreferredItemDTOMapper preferredItemDTOMapper;

    @Override
    public ItemDTO mapEntity(Item entity) {
        if (entity == null) {
            return null;
        }

        ItemDTO dto = new ItemDTO();
        dto.setId(entity.getId());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setVersion(entity.getVersion());
        dto.setDeleted(entity.isDeleted());

        dto.setUserId(entity.getUserId());
        dto.setStatus(entity.getStatus().toString());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setBrand(entity.getBrand());
        dto.setNew(entity.isNew());
        dto.setCategory(entity.getCategory());
        dto.setStrict(entity.isStrict());
        dto.setLikes(entity.getLikes());
        dto.setPreferredCats(entity.getPreferredCats());

        List<PreferredItemDTO> preferredItemDTOList = new ArrayList<>();
        for (PreferredItem preferredItem : entity.getPreferredItems()) {
            Preconditions.checkNotNull(preferredItem);
            preferredItemDTOList.add(preferredItemDTOMapper.mapEntity(preferredItem));
        }
        dto.setPreferredItems(preferredItemDTOList);

        List<ItemHistoryDTO> itemHistoryDTOList = new ArrayList<>();
        for (ItemHistory itemHistory : entity.getItemHistory()) {
            Preconditions.checkNotNull(itemHistory);
            itemHistoryDTOList.add(itemHistoryDTOMapper.mapEntity(itemHistory));
        }
        dto.setItemHistory(itemHistoryDTOList);

        return dto;
    }

    @Override
    public Item mapDto(ItemDTO dto) {
        if (dto == null) {
            return null;
        }

        Item entity = new Item();
        entity.setId(dto.getId());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLastModifiedBy(dto.getLastModifiedBy());
        entity.setLastModifiedDate(dto.getLastModifiedDate());

        entity.setUserId(dto.getUserId());
        entity.setStatus(Item.Status.valueOf(dto.getStatus()));
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setBrand(dto.getBrand());
        entity.setNew(dto.isNew());
        entity.setCategory(dto.getCategory());
        entity.setStrict(dto.isStrict());
        entity.setLikes(dto.getLikes());
        entity.setPreferredCats(dto.getPreferredCats());

        List<PreferredItem> preferredItemList = new ArrayList<>();
        for (PreferredItemDTO preferredItemDTO : dto.getPreferredItems()) {
            Preconditions.checkNotNull(preferredItemDTO);
            preferredItemList.add(preferredItemDTOMapper.mapDto(preferredItemDTO));
        }
        entity.setPreferredItems(preferredItemList);

        List<ItemHistory> itemHistoryList = new ArrayList<>();
        for (ItemHistoryDTO itemHistoryDTO : dto.getItemHistory()) {
            Preconditions.checkNotNull(itemHistoryDTO);
            itemHistoryList.add(itemHistoryDTOMapper.mapDto(itemHistoryDTO));
        }
        entity.setItemHistory(itemHistoryList);

        return entity;
    }
}
