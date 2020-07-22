package com.swappee.mapper.review;

import com.swappee.domain.review.Review;
import com.swappee.model.review.ReviewDTO;

public class ReviewDTOMapper {
    public ReviewDTO mapEntity(Review entity) {
        if (entity == null) {
            return null;
        }

        ReviewDTO dto = new ReviewDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());

        dto.setRequestId(entity.getRequestId());
        dto.setOwnerId(entity.getOwnerId());
        dto.setTraderId(entity.getTraderId());
        dto.setScore(entity.getScore());
        dto.setRemarks(entity.getRemarks());

        return dto;
    }

    public Review mapDto(ReviewDTO dto) {
        if (dto == null) {
            return null;
        }

        Review entity = new Review();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());

        entity.setRequestId(dto.getRequestId());
        entity.setOwnerId(dto.getOwnerId());
        entity.setTraderId(dto.getTraderId());
        entity.setScore(dto.getScore());
        entity.setRemarks(dto.getRemarks());

        return entity;
    }
}
