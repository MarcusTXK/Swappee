package com.swappee.mapper.item;

import com.swappee.domain.item.PreferredItem;
import com.swappee.model.item.PreferredItemDTO;
import org.springframework.stereotype.Component;

@Component
public class PreferredItemDTOMapper {
    public PreferredItemDTO mapEntity(PreferredItem entity) {
        if (entity == null) {
            return null;
        }

        PreferredItemDTO dto = new PreferredItemDTO();
        dto.setName(entity.getName());
        dto.setBrand(entity.getBrand());
        dto.setNew(entity.isNew());
        dto.setCategory(dto.getCategory());

        return dto;
    }

    public PreferredItem mapDto(PreferredItemDTO dto) {
        if (dto == null) {
            return null;
        }

        PreferredItem entity = new PreferredItem();
        entity.setName(dto.getName());
        entity.setBrand(dto.getBrand());
        entity.setNew(dto.isNew());
        entity.setCategory(entity.getCategory());

        return entity;
    }
}
