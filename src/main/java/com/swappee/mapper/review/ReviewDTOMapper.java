package com.swappee.mapper.review;

import com.swappee.domain.review.Review;
import com.swappee.mapper.DTOMapper;
import com.swappee.model.review.ReviewDTO;
import org.springframework.stereotype.Component;

@Component
public class ReviewDTOMapper implements DTOMapper<ReviewDTO, Review> {

    @Override
    public ReviewDTO mapEntity(Review entity) {
        if (entity == null) {
            return null;
        }

        ReviewDTO dto = new ReviewDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setDeleted(entity.isDeleted());

        dto.setRequestId(entity.getRequestId());
        dto.setReviewerId(entity.getReviewerId());
        dto.setReviewedId(entity.getReviewedId());
        dto.setScore(entity.getScore());
        dto.setRemarks(entity.getRemarks());

        return dto;
    }

    @Override
    public Review mapDto(ReviewDTO dto) {
        if (dto == null) {
            return null;
        }

        Review entity = new Review();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setDeleted(dto.isDeleted());

        entity.setRequestId(dto.getRequestId());
        entity.setReviewerId(dto.getReviewerId());
        entity.setReviewedId(dto.getReviewedId());
        entity.setScore(dto.getScore());
        entity.setRemarks(dto.getRemarks());

        return entity;
    }
}
