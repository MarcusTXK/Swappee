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
 * Public REST controller for managing items.
 * Only has Get methods
 */

@RestController
@RequestMapping("/api/public/item")
public class ItemPublicApiController {
    private static final Logger logger = LoggerFactory.getLogger(ItemPublicApiController.class);

    @Autowired
    ItemService itemService;

    /**
     * Api to get item view by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContentResult> getItemView(@PathVariable Long id) {
        logger.info("Start getItemView - id: {}", id);
        ContentResult contentResult = new ContentResult();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Preconditions.checkNotNull(id);
            ItemViewDTO itemViewDTO = this.itemService.findItemViewById(id);
            contentResult.setIsSuccess(true);
            contentResult.setData(itemViewDTO);
            contentResult.setMessage(UserFriendlyMessage.ITEM_GET_ONE_SUCCEED);
        } catch (BaseServiceException bse) {
            logger.error("Error in getItemView():", bse);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.ITEM_GET_ONE_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in getItemView():", e);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.ITEM_GET_ONE_FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End getItemView");
        return new ResponseEntity<>(contentResult, httpStatus);
    }

    /**
     * Api to get all item views by pageable and category filters
     * @param currentPage
     * @param filter
     * @param sortColumn
     * @param sortDir
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<GridResult> getAllItemViews(@RequestParam(defaultValue = "1") Integer currentPage,
                                             @RequestParam(defaultValue = "all") String filter,
                                             @RequestParam(defaultValue = "createdDate") String sortColumn,
                                             @RequestParam(defaultValue = "asc") String sortDir,
                                             @RequestParam(defaultValue = "12") Integer pageSize) {
        logger.info("Start getAllItemViews - currentPage: {}, filter: {}, sortColumn: {}, sortDir: {}, pageSize:{}", currentPage, filter, sortColumn, sortDir, pageSize);
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
            logger.error("Error in getAllItemViews():", bse);
            gridResult.setIsSuccess(false);
            gridResult.setMessage(UserFriendlyMessage.ITEM_GET_LIST_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in getAllItemViews():", e);
            gridResult.setIsSuccess(false);
            gridResult.setMessage(UserFriendlyMessage.ITEM_GET_LIST_FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End getAllItemViews");
        return new ResponseEntity<>(gridResult, httpStatus);
    }

    /**
     * Api to get item views by used's id
     * @param currentPage
     * @param sortColumn
     * @param sortDir
     * @param pageSize
     * @param userId
     * @return
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<GridResult> findItemViewsByUserId(@RequestParam(defaultValue = "1") Integer currentPage,
                                                        @RequestParam(defaultValue = "createdDate") String sortColumn,
                                                        @RequestParam(defaultValue = "asc") String sortDir,
                                                        @RequestParam(defaultValue = "12") Integer pageSize,
                                                        @PathVariable Long userId) {
        logger.info("Start findItemViewsByUserId - currentPage: {}, sortColumn: {}, sortDir: {}, pageSize:{}, userId:{}", currentPage, sortColumn, sortDir, pageSize, userId);
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
            logger.error("Error in findItemViewsByUserId():", bse);
            gridResult.setIsSuccess(false);
            gridResult.setMessage(UserFriendlyMessage.ITEM_GET_LIST_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in findItemViewsByUserId():", e);
            gridResult.setIsSuccess(false);
            gridResult.setMessage(UserFriendlyMessage.ITEM_GET_LIST_FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End findItemViewsByUserId");
        return new ResponseEntity<>(gridResult, httpStatus);
    }

}
