package com.swappee.service.picture;

import com.google.common.base.Preconditions;
import com.swappee.dao.picture.PictureDao;
import com.swappee.domain.picture.Picture;
import com.swappee.mapper.picture.PictureDTOMapper;
import com.swappee.model.item.ItemDTO;
import com.swappee.model.picture.PictureDTO;
import com.swappee.utils.exception.BaseDaoException;
import com.swappee.utils.exception.BaseServiceException;
import com.swappee.utils.exception.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    @Transactional(rollbackFor = {BaseServiceException.class})
    public Boolean create(List<PictureDTO> pictureDTOList) throws BaseServiceException {
        try {
            logger.info("Start create - pictureDTOList: {}", pictureDTOList);
            Preconditions.checkArgument(!pictureDTOList.isEmpty());
            List<Picture> pictureList = new ArrayList<>();
            for (PictureDTO pictureDTO : pictureDTOList) {
                pictureList.add(pictureDTOMapper.mapDto(pictureDTO));
            }
            List<Picture> createdPictureList = pictureDao.create(pictureList);
            Preconditions.checkArgument(!createdPictureList.isEmpty());
            return true;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.PICTURE_ERROR_CREATE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End create");
        }
    }

    @Override
    @Transactional(rollbackFor = {BaseServiceException.class})
    public Boolean update(List<PictureDTO> pictureDTOList) throws BaseServiceException {
        try {
            logger.info("Start update - pictureDTOList: {}", pictureDTOList);
            Preconditions.checkArgument(!pictureDTOList.isEmpty());
            List<Picture> pictureList = new ArrayList<>();
            for (PictureDTO pictureDTO : pictureDTOList) {
                pictureList.add(pictureDTOMapper.mapDto(pictureDTO));
            }
            pictureDao.update(pictureList);
            return true;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.PICTURE_ERROR_UPDATE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End update");
        }
    }
}
