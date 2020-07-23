package com.swappee.mapper.request;

import com.swappee.domain.request.Request;
import com.swappee.model.request.RequestDTO;
import org.springframework.stereotype.Component;

@Component
public class RequestDTOMapper {
    public RequestDTO mapEntity(Request entity) {
        if (entity == null) {
            return null;
        }

        RequestDTO dto = new RequestDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLastModifiedDate(entity.getLastModifiedDate());

        dto.setOwnerId(entity.getOwnerId());
        dto.setTraderId(entity.getTraderId());
        dto.setItemId(entity.getItemId());
        dto.setStatus(entity.getStatus().toString());
        dto.setRemarks(entity.getRemarks());
        dto.setHidden(entity.isHidden());

        return dto;
    }

    public Request mapDto(RequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Request entity = new Request();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLastModifiedDate(dto.getLastModifiedDate());

        entity.setOwnerId(dto.getOwnerId());
        entity.setTraderId(dto.getTraderId());
        entity.setItemId(dto.getItemId());
        entity.setStatus(Request.Status.valueOf(dto.getStatus()));
        entity.setRemarks(dto.getRemarks());
        entity.setHidden(dto.isHidden());

        return entity;
    }
}
