package com.swappee.controller.privateapi.item;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.swappee.model.item.ItemDTO;
import com.swappee.model.picture.PictureDTO;
import com.swappee.model.wrapper.ContentResult;
import com.swappee.model.wrapper.GridResult;
import com.swappee.service.item.ItemService;
import com.swappee.service.picture.PictureService;
import com.swappee.utils.exception.BaseServiceException;
import com.swappee.utils.exception.UserFriendlyMessage;
import com.swappee.utils.picture.PictureUtil;
import com.swappee.utils.security.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing items
 */

@RestController
@RequestMapping("/api/private/item")
public class ItemPrivateApiController {
    private static final Logger logger = LoggerFactory.getLogger(ItemPrivateApiController.class);

    @Autowired
    ItemService itemService;

    @Autowired
    PictureService pictureService;

    @Autowired
    SecurityUtil securityUtil;

    @Autowired
    PictureUtil pictureUtil;

    /**
     * Api to get item by id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContentResult> getItem(@PathVariable Long id) {
        logger.info("Start getItem - id: {}", id);
        ContentResult contentResult = new ContentResult();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Preconditions.checkNotNull(id);
            ItemDTO itemDTO = this.itemService.findItemById(id);
            contentResult.setIsSuccess(true);
            contentResult.setData(itemDTO);
            contentResult.setMessage(UserFriendlyMessage.ITEM_GET_ONE_SUCCEED);
        } catch (BaseServiceException bse) {
            logger.error("Error in getItem():", bse);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.ITEM_GET_ONE_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in getItem():", e);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.ITEM_GET_ONE_FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End getItem");
        return new ResponseEntity<>(contentResult, httpStatus);
    }

    @GetMapping("/{id}/{liked}")
    public ResponseEntity<ContentResult> itemLiked(@PathVariable Long id, @PathVariable Boolean liked) {
        logger.info("Start itemLiked - id: {}, liked: {}", id, liked);
        ContentResult contentResult = new ContentResult();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Long userId = securityUtil.getAuthenticatedUserId();
            Preconditions.checkNotNull(id);
            ItemDTO itemDTO = this.itemService.itemLiked(id, userId, liked);
            contentResult.setIsSuccess(true);
            contentResult.setData(itemDTO);
            contentResult.setMessage(UserFriendlyMessage.ITEM_LIKE_SUCCEED);
        } catch (BaseServiceException bse) {
            logger.error("Error in itemLiked():", bse);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.ITEM_LIKE_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in itemLiked():", e);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.ITEM_LIKE_FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End itemLiked");
        return new ResponseEntity<>(contentResult, httpStatus);
    }

    /**
     * Api to get Item views by items a user has liked
     *
     * @param currentPage
     * @param sortColumn
     * @param sortDir
     * @param pageSize
     * @return
     */
    @GetMapping("/likes")
    public ResponseEntity<GridResult> findItemViewsByUserLikes(@RequestParam(defaultValue = "1") Integer currentPage,
                                                               @RequestParam(defaultValue = "createdDate") String sortColumn,
                                                               @RequestParam(defaultValue = "asc") String sortDir,
                                                               @RequestParam(defaultValue = "12") Integer pageSize) {
        logger.info("Start findItemViewsByUserLikes - currentPage: {}, sortColumn: {}, sortDir: {}, pageSize:{}", currentPage, sortColumn, sortDir, pageSize);
        GridResult gridResult = new GridResult();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Long userId = securityUtil.getAuthenticatedUserId();
            Sort sort;
            if (sortDir.equals("asc")) {
                sort = Sort.by(sortColumn).ascending();
            } else {
                sort = Sort.by(sortColumn).descending();
            }
            Pageable pageable = PageRequest.of(currentPage - 1, pageSize, sort);
            gridResult = itemService.findItemsByUserLikes(userId, pageable);
            gridResult.setMessage(UserFriendlyMessage.ITEM_GET_LIST_SUCCEED);
        } catch (BaseServiceException bse) {
            logger.error("Error in findItemViewsByUserLikes():", bse);
            gridResult.setIsSuccess(false);
            gridResult.setMessage(UserFriendlyMessage.ITEM_GET_LIST_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in findItemViewsByUserLikes():", e);
            gridResult.setIsSuccess(false);
            gridResult.setMessage(UserFriendlyMessage.ITEM_GET_LIST_FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End findItemViewsByUserLikes");
        return new ResponseEntity<>(gridResult, httpStatus);
    }

    /**
     * Api to create Items and Pictures
     *
     * @param jsonString
     * @param photos
     * @param descriptions
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ContentResult> createItem(@RequestParam("itemDTO") String jsonString, @RequestParam("photos") MultipartFile[] photos, @RequestParam("descriptions") String[] descriptions) throws JsonProcessingException {
        logger.info("Start createItem - itemDTO: {}, photos: {}, descriptions: {}", jsonString, photos, descriptions);
        ContentResult contentResult = new ContentResult();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            ItemDTO itemDTO = new ObjectMapper().readValue(jsonString, ItemDTO.class);
            Long userId = securityUtil.getAuthenticatedUserId();
            //Ensure use logged in is same user who is creating the item
            Preconditions.checkArgument(itemDTO.getUserId().equals(userId));
            Preconditions.checkArgument(photos.length == descriptions.length);

            ItemDTO createdItemDTO = itemService.create(itemDTO);
            List<PictureDTO> pictureDTOList = new ArrayList<>();
            for (int i = 0; i < photos.length; i++) {

                PictureDTO pictureDTO = pictureUtil.processImagetoPictureDTO(photos[i]);
                pictureDTO.setItemId(createdItemDTO.getId());
                pictureDTO.setOrder((long) i);
//                pictureDTO.setFileData(photos[i].getBytes());
                pictureDTO.setFileName(itemDTO.getName() + "_" + i + ".jpeg");
//                pictureDTO.setContentType(photos[i].getContentType());
//                pictureDTO.setContentLength(photos[i].getSize());
                Preconditions.checkNotNull(descriptions[i]);
                pictureDTO.setDescription(descriptions[i]);
                pictureDTOList.add(pictureDTO);
            }
            //create picture DTO list
            contentResult.setIsSuccess(pictureService.create(pictureDTOList));
            contentResult.setData(createdItemDTO);
            contentResult.setMessage(UserFriendlyMessage.ITEM_CREATE_SUCCEED);
        } catch (BaseServiceException bse) {
            logger.error("Error in createItem():", bse);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.ITEM_CREATE_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in createItem():", e);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.ITEM_CREATE_FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End createItem");
        return new ResponseEntity<>(contentResult, httpStatus);
    }

    /**
     * Api to update Items and Pictures
     *
     * @param jsonString
     * @param photos
     * @param descriptions
     * @return
     * @throws JsonProcessingException
     */
    @PutMapping(value = {"/id"} ,consumes = {"multipart/form-data"})
    public ResponseEntity<ContentResult> updateItem(@RequestParam("itemDTO") String jsonString, @RequestParam("photos") MultipartFile[] photos, @RequestParam("descriptions") String[] descriptions) throws JsonProcessingException {
        logger.info("Start updateItem - itemDTO: {}, photos: {}, descriptions: {}", jsonString, photos, descriptions);
        ContentResult contentResult = new ContentResult();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            ItemDTO itemDTO = new ObjectMapper().readValue(jsonString, ItemDTO.class);
            Long userId = securityUtil.getAuthenticatedUserId();
            //Ensure use logged in is same user who is creating the item
            Preconditions.checkArgument(itemDTO.getUserId().equals(userId));
            Preconditions.checkArgument(photos.length == descriptions.length);

            ItemDTO createdItemDTO = itemService.update(itemDTO);
            List<PictureDTO> pictureDTOList = new ArrayList<>();
            for (int i = 0; i < photos.length; i++) {
                PictureDTO pictureDTO = new PictureDTO();
                pictureDTO.setItemId(createdItemDTO.getId());
                pictureDTO.setOrder((long) i);
                pictureDTO.setFileData(photos[i].getBytes());
                pictureDTO.setFileName(itemDTO.getName() + "_" + i);
                pictureDTO.setContentType(photos[i].getContentType());
                pictureDTO.setContentLength(photos[i].getSize());
                Preconditions.checkNotNull(descriptions[i]);
                pictureDTO.setDescription(descriptions[i]);
                pictureDTOList.add(pictureDTO);
            }
            //create picture DTO list
            contentResult.setIsSuccess(pictureService.update(pictureDTOList));
            contentResult.setData(createdItemDTO);
            contentResult.setMessage(UserFriendlyMessage.ITEM_UPDATE_SUCCEED);
        } catch (BaseServiceException bse) {
            logger.error("Error in updateItem():", bse);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.ITEM_UPDATE_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in updateItem():", e);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.ITEM_UPDATE_FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End updateItem");
        return new ResponseEntity<>(contentResult, httpStatus);
    }


}
