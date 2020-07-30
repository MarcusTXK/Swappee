package com.swappee.service.picture;

import com.swappee.model.picture.PictureDTO;
import com.swappee.utils.exception.BaseServiceException;

import java.util.List;

/**
 * Service interface for managing pictures
 * Pictures are created and updated via ItemService
 */
public interface PictureService {
    PictureDTO findById(Long id) throws BaseServiceException;

    Boolean create(List<PictureDTO> pictureDTOList) throws BaseServiceException;

    Boolean update(List<PictureDTO> pictureDTOList) throws BaseServiceException;
}
