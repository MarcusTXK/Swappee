package com.swappee.mapper.like;

import com.swappee.domain.like.Like;
import com.swappee.model.like.LikeDTO;

public class LikeDTOMapper {
    public LikeDTO mapEntity(Like entity) {
        if (entity == null) {
            return null;
        }

        LikeDTO dto = new LikeDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());

        dto.setItemId(entity.getItemId());
        dto.setUserId(entity.getUserId());
        return dto;
    }

    public Like mapDto(LikeDTO dto) {
        if (dto == null) {
            return null;
        }

        Like entity = new Like();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());

        entity.setItemId(dto.getItemId());
        entity.setUserId(dto.getUserId());

        return entity;
    }
}
