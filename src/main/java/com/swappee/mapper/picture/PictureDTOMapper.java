package com.swappee.mapper.picture;

import com.swappee.domain.picture.Picture;
import com.swappee.mapper.DTOMapper;
import com.swappee.model.picture.PictureDTO;
import org.springframework.stereotype.Component;

@Component
public class PictureDTOMapper implements DTOMapper<PictureDTO, Picture> {

    @Override
    public PictureDTO mapEntity(Picture entity) {
        if (entity == null) {
            return null;
        }

        PictureDTO dto = new PictureDTO();
        dto.setId(entity.getId());

        dto.setItemId(entity.getItemId());
        dto.setOrder(entity.getOrder());
        dto.setFileData(entity.getFileData());
        dto.setFileName(entity.getFileName());
        dto.setContentType(entity.getContentType());
        dto.setContentLength(entity.getContentLength());
        dto.setDescription(entity.getDescription());

        return dto;
    }

    @Override
    public Picture mapDto(PictureDTO dto) {
        if (dto == null) {
            return null;
        }

        Picture entity = new Picture();
        entity.setId(dto.getId());

        entity.setItemId(dto.getItemId());
        entity.setOrder(dto.getOrder());
        entity.setFileData(dto.getFileData());
        entity.setFileName(dto.getFileName());
        entity.setContentType(dto.getContentType());
        entity.setContentLength(dto.getContentLength());
        entity.setDescription(dto.getDescription());

        return entity;
    }
}
