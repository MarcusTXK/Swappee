package com.swappee.service.picture;

import com.google.common.base.Preconditions;
import com.swappee.dao.picture.PictureDao;
import com.swappee.mapper.picture.PictureDTOMapper;
import com.swappee.model.picture.PictureDTO;
import com.swappee.utils.exception.BaseDaoException;
import com.swappee.utils.exception.BaseServiceException;
import com.swappee.utils.exception.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation class for managing pictures.
 */
@Service
public class PictureServiceImpl implements PictureService{
    private static final Logger logger = LoggerFactory.getLogger(PictureServiceImpl.class);

    @Autowired
    PictureDao pictureDao;

    @Autowired
    PictureDTOMapper pictureDTOMapper;

    @Override
    public PictureDTO findById(Long id) throws BaseServiceException {
        try {
            logger.info("Start findById - id: {}", id);
            Preconditions.checkNotNull(id);

            PictureDTO pictureDTO = pictureDTOMapper.mapEntity(pictureDao.findById(id));
            Preconditions.checkNotNull(pictureDTO);
            return pictureDTO;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.PICTURE_ERROR_GET_ONE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findById");
        }
    }
}
