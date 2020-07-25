package com.swappee.mapper.item;

import com.swappee.domain.item.PreferredItem;
import com.swappee.mapper.DTOMapper;
import com.swappee.model.item.PreferredItemDTO;
import org.springframework.stereotype.Component;

@Component
public class PreferredItemDTOMapper implements DTOMapper<PreferredItemDTO, PreferredItem> {

    @Override
    public PreferredItemDTO mapEntity(PreferredItem entity) {
        if (entity == null) {
            return null;
        }

        PreferredItemDTO dto = new PreferredItemDTO();
        dto.setName(entity.getName());
        dto.setBrand(entity.getBrand());
        dto.setNew(entity.isNew());
        dto.setCategory(entity.getCategory());

        return dto;
    }

    @Override
    public PreferredItem mapDto(PreferredItemDTO dto) {
        if (dto == null) {
            return null;
        }

        PreferredItem entity = new PreferredItem();
        entity.setName(dto.getName());
        entity.setBrand(dto.getBrand());
        entity.setNew(dto.isNew());
        entity.setCategory(dto.getCategory());

        return entity;
    }
}
