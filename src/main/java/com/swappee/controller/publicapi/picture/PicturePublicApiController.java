package com.swappee.controller.publicapi.picture;

import com.google.common.base.Preconditions;
import com.swappee.model.picture.PictureDTO;
import com.swappee.model.picture.PictureViewDTO;
import com.swappee.model.wrapper.ContentResult;
import com.swappee.service.picture.PictureService;
import com.swappee.utils.exception.BaseServiceException;
import com.swappee.utils.exception.UserFriendlyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Public REST controller for managing pictures.
 * Only has Get methods
 */

@RestController
@RequestMapping("/api/public/picture")
public class PicturePublicApiController {
    private static final Logger logger = LoggerFactory.getLogger(PicturePublicApiController.class);

    @Autowired
    PictureService pictureService;

    /**
     * Api to display a picture by id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable Long id) {
        logger.info("Start download - id: {}", id);
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Preconditions.checkNotNull(id);
            PictureDTO pictureDTO = pictureService.findById(id);
            if (pictureDTO != null) {
                return ResponseEntity
                        .ok()
                        .header(HttpHeaders.CONTENT_TYPE, pictureDTO.getContentType())
                        .header(HttpHeaders.CONTENT_LENGTH, pictureDTO.getContentLength().toString())
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "Content-Disposition: inline; filename=\"" + pictureDTO.getFileName() + "\"")
                        .body(new ByteArrayResource(pictureDTO.getFileData()));
            }
        }  catch (BaseServiceException bse) {
            logger.error("Error in download():" , bse);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in download():" , e);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End download");
        return new ResponseEntity<>(httpStatus);
    }

    @GetMapping("item/{itemId}")
    public ResponseEntity<ContentResult> findByItemId(@PathVariable Long itemId) {
        logger.info("Start findByItemId - itemId: {}", itemId);
        ContentResult contentResult = new ContentResult();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Preconditions.checkNotNull(itemId);
            List<PictureViewDTO> pictureViewDTOList = this.pictureService.findByItemId(itemId);
            contentResult.setIsSuccess(true);
            contentResult.setData(pictureViewDTOList);
            contentResult.setMessage(UserFriendlyMessage.PICTURE_GET_LIST_SUCCEED);
        } catch (BaseServiceException bse) {
            logger.error("Error in findByItemId():", bse);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.PICTURE_GET_LIST_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in findByItemId():", e);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.PICTURE_GET_LIST_FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End findByItemId");
        return new ResponseEntity<>(contentResult, httpStatus);
    }
}
