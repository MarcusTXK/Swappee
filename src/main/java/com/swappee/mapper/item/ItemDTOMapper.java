package com.swappee.mapper.item;

import com.swappee.domain.item.Item;
import com.swappee.model.item.ItemDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemDTOMapper {
    public ItemDTO mapEntity(Item entity) {
        if (entity == null) {
            return null;
        }

        ItemDTO dto = new ItemDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLastModifiedDate(entity.getLastModifiedDate());

        dto.setUserId(entity.getUserId());
        dto.setStatus(entity.getStatus().toString());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setBrand(entity.getBrand());
        dto.setNew(entity.isNew());
        dto.setCategory(dto.getCategory());
        dto.setStrict(entity.isStrict());
        dto.setPreferredCats(entity.getPreferredCats());
        dto.setPreferredItems(entity.getPreferredItems());

        return dto;
    }

    public Item mapDto(ItemDTO dto) {
        if (dto == null) {
            return null;
        }

        Item entity = new Item();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLastModifiedDate(dto.getLastModifiedDate());

        entity.setUserId(dto.getUserId());
        entity.setStatus(Item.Status.valueOf(dto.getStatus()));
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setBrand(dto.getBrand());
        entity.setNew(dto.isNew());
        entity.setCategory(entity.getCategory());
        entity.setStrict(dto.isStrict());
        entity.setPreferredCats(dto.getPreferredCats());
        entity.setPreferredItems(dto.getPreferredItems());

        return entity;
    }
}
