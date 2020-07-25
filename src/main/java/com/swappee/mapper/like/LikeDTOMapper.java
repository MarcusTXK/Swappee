package com.swappee.mapper.like;

import com.swappee.domain.like.Like;
import com.swappee.mapper.DTOMapper;
import com.swappee.model.like.LikeDTO;
import org.springframework.stereotype.Component;

@Component
public class LikeDTOMapper implements DTOMapper<LikeDTO, Like> {

    @Override
    public LikeDTO mapEntity(Like entity) {
        if (entity == null) {
            return null;
        }

        LikeDTO dto = new LikeDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setItemDeleted(entity.isItemDeleted());

        dto.setItemId(entity.getItemId());
        dto.setUserId(entity.getUserId());
        return dto;
    }

    @Override
    public Like mapDto(LikeDTO dto) {
        if (dto == null) {
            return null;
        }

        Like entity = new Like();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setItemDeleted(dto.isItemDeleted());

        entity.setItemId(dto.getItemId());
        entity.setUserId(dto.getUserId());

        return entity;
    }
}
