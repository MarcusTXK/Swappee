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
        dto.setOwnerItemId(entity.getOwnerItemId());
        dto.setTraderItemId(entity.getTraderItemId());
        dto.setStatus(entity.getStatus().toString());
        dto.setRemarks(entity.getRemarks());
        dto.setOwnerReviewed(entity.isOwnerReviewed());
        dto.setTraderReviewed(entity.isTraderReviewed());
        dto.setOwnerHidden(entity.isOwnerHidden());
        dto.setTraderHidden(entity.isTraderHidden());

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
        entity.setOwnerItemId(dto.getOwnerItemId());
        entity.setTraderItemId(dto.getTraderItemId());
        entity.setStatus(Request.Status.valueOf(dto.getStatus()));
        entity.setRemarks(dto.getRemarks());
        entity.setOwnerReviewed(dto.isOwnerReviewed());
        entity.setTraderReviewed(dto.isTraderReviewed());
        entity.setOwnerHidden(dto.isOwnerHidden());
        entity.setTraderHidden(dto.isTraderHidden());

        return entity;
    }
}
