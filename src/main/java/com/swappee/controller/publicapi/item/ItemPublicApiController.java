package com.swappee.controller.publicapi.item;

import com.google.common.base.Preconditions;
import com.swappee.model.item.ItemViewDTO;
import com.swappee.model.wrapper.ContentResult;
import com.swappee.model.wrapper.GridResult;
import com.swappee.service.item.ItemService;
import com.swappee.utils.exception.BaseServiceException;
import com.swappee.utils.exception.UserFriendlyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * REST controller for managing items.
 */

@RestController
@RequestMapping("/api/public/item")
public class ItemPublicApiController {
    private static final Logger logger = LoggerFactory.getLogger(ItemPublicApiController.class);

    @Autowired
    ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<ContentResult> getItem(@PathVariable Long id) {
        logger.info("Start getItem - id: {}", id);
        ContentResult contentResult = new ContentResult();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Preconditions.checkNotNull(id);
            ItemViewDTO itemViewDTO = this.itemService.findItemViewById(id);
            contentResult.setIsSuccess(true);
            contentResult.setData(itemViewDTO);
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

    @GetMapping("/list")
    public ResponseEntity<GridResult> getAll(@RequestParam(defaultValue = "1") Integer currentPage,
                                             @RequestParam(defaultValue = "all") String filter,
                                             @RequestParam(defaultValue = "createdDate") String sortColumn,
                                             @RequestParam(defaultValue = "asc") String sortDir,
                                             @RequestParam(defaultValue = "12") Integer pageSize) {
        logger.info("Start getAll - currentPage: {}, filter: {}, sortColumn: {}, sortDir: {}, pageSize:{}", currentPage, filter, sortColumn, sortDir, pageSize);
        GridResult gridResult = new GridResult();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Sort sort;
            if (sortDir.equals("asc")) {
                sort = Sort.by(sortColumn).ascending();
            } else {
                sort = Sort.by(sortColumn).descending();
            }

            Pageable pageable = PageRequest.of(currentPage - 1, pageSize, sort);
            if (filter.equalsIgnoreCase("all")) {
                gridResult = itemService.findItems(pageable);
            } else {
                gridResult = itemService.findByCategory(filter.toLowerCase(), pageable);
            }
            gridResult.setMessage(UserFriendlyMessage.ITEM_GET_LIST_SUCCEED);
        } catch (BaseServiceException bse) {
            logger.error("Error in getAll():", bse);
            gridResult.setIsSuccess(false);
            gridResult.setMessage(UserFriendlyMessage.ITEM_GET_LIST_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in getAll():", e);
            gridResult.setIsSuccess(false);
            gridResult.setMessage(UserFriendlyMessage.ITEM_GET_LIST_FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End getAll");
        return new ResponseEntity<>(gridResult, httpStatus);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<GridResult> findItemsByUserId(@RequestParam(defaultValue = "1") Integer currentPage,
                                                        @RequestParam(defaultValue = "createdDate") String sortColumn,
                                                        @RequestParam(defaultValue = "asc") String sortDir,
                                                        @RequestParam(defaultValue = "12") Integer pageSize,
                                                        @PathVariable Long userId) {
        logger.info("Start findItemsByUserId - currentPage: {}, sortColumn: {}, sortDir: {}, pageSize:{}, userId:{}", currentPage, sortColumn, sortDir, pageSize, userId);
        GridResult gridResult = new GridResult();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Sort sort;
            if (sortDir.equals("asc")) {
                sort = Sort.by(sortColumn).ascending();
            } else {
                sort = Sort.by(sortColumn).descending();
            }
            Pageable pageable = PageRequest.of(currentPage - 1, pageSize, sort);
            gridResult = itemService.findItemsByUserId(userId, pageable);
            gridResult.setMessage(UserFriendlyMessage.ITEM_GET_LIST_SUCCEED);
        } catch (BaseServiceException bse) {
            logger.error("Error in findItemsByUserId():", bse);
            gridResult.setIsSuccess(false);
            gridResult.setMessage(UserFriendlyMessage.ITEM_GET_LIST_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in findItemsByUserId():", e);
            gridResult.setIsSuccess(false);
            gridResult.setMessage(UserFriendlyMessage.ITEM_GET_LIST_FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End findItemsByUserId");
        return new ResponseEntity<>(gridResult, httpStatus);
    }

    @GetMapping("/likes/{userId}")
    public ResponseEntity<GridResult> findItemsByUserLikes(@RequestParam(defaultValue = "1") Integer currentPage,
                                                        @RequestParam(defaultValue = "createdDate") String sortColumn,
                                                        @RequestParam(defaultValue = "asc") String sortDir,
                                                        @RequestParam(defaultValue = "12") Integer pageSize,
                                                        @PathVariable Long userId) {
        logger.info("Start findItemsByUserLikes - currentPage: {}, sortColumn: {}, sortDir: {}, pageSize:{}, userId:{}", currentPage, sortColumn, sortDir, pageSize, userId);
        GridResult gridResult = new GridResult();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
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
            logger.error("Error in findItemsByUserLikes():", bse);
            gridResult.setIsSuccess(false);
            gridResult.setMessage(UserFriendlyMessage.ITEM_GET_LIST_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in findItemsByUserLikes():", e);
            gridResult.setIsSuccess(false);
            gridResult.setMessage(UserFriendlyMessage.ITEM_GET_LIST_FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End findItemsByUserLikes");
        return new ResponseEntity<>(gridResult, httpStatus);
    }



}
