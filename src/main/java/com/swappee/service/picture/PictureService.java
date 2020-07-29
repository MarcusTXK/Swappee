package com.swappee.service.picture;

import com.swappee.model.picture.PictureDTO;
import com.swappee.utils.exception.BaseServiceException;

/**
 * Service interface for managing pictures
 * Pictures are created and updated via ItemService
 */
public interface PictureService {
    PictureDTO findById(Long id) throws BaseServiceException;
}
