package com.swappee.controller.privateapi.request;

import com.google.common.base.Preconditions;
import com.swappee.domain.request.Request;
import com.swappee.model.item.ItemDTO;
import com.swappee.model.request.RequestDTO;
import com.swappee.model.wrapper.ContentResult;
import com.swappee.model.wrapper.GridResult;
import com.swappee.service.request.RequestService;
import com.swappee.utils.exception.BaseServiceException;
import com.swappee.utils.exception.UserFriendlyMessage;
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

import javax.validation.Valid;

/**
 * REST controller for managing requests
 */
@RestController
@RequestMapping("/api/private/request")
public class RequestPrivateController {
    private static final Logger logger = LoggerFactory.getLogger(RequestPrivateController.class);

    @Autowired
    RequestService requestService;

    @Autowired
    SecurityUtil securityUtil;

    /**
     * Api to get Requests made to the user
     *
     * @param currentPage
     * @param sortColumn
     * @param sortDir
     * @param pageSize
     * @return
     */

    @GetMapping("/owner")
    public ResponseEntity<GridResult> getByOwnerId(@RequestParam(defaultValue = "1") Integer currentPage,
                                                   @RequestParam(defaultValue = "createdDate") String sortColumn,
                                                   @RequestParam(defaultValue = "asc") String sortDir,
                                                   @RequestParam(defaultValue = "12") Integer pageSize) {
        logger.info("Start getByOwnerId - currentPage: {}, sortColumn: {}, sortDir: {}, pageSize:{}", currentPage, sortColumn, sortDir, pageSize);
        GridResult gridResult = new GridResult();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            logger.info("Entering function");
            Long userId = securityUtil.getAuthenticatedUserId();
            logger.info("userId: {}", userId);
            Sort sort;
            if (sortDir.equals("asc")) {
                sort = Sort.by(sortColumn).ascending();
            } else {
                sort = Sort.by(sortColumn).descending();
            }
            Pageable pageable = PageRequest.of(currentPage - 1, pageSize, sort);
            gridResult = requestService.findByOwnerId(userId, pageable);
            gridResult.setIsSuccess(true);
            gridResult.setMessage(UserFriendlyMessage.REQUEST_GET_LIST_SUCCEED);
        } catch (BaseServiceException bse) {
            logger.error("Error in getByOwnerId():", bse);
            gridResult.setIsSuccess(false);
            gridResult.setMessage(UserFriendlyMessage.REQUEST_GET_LIST_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in getByOwnerId():", e);
            gridResult.setIsSuccess(false);
            gridResult.setMessage(UserFriendlyMessage.REQUEST_GET_LIST_FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End getByOwnerId");
        return new ResponseEntity<>(gridResult, httpStatus);
    }

    /**
     * Api to get Requests made by User
     *
     * @param currentPage
     * @param sortColumn
     * @param sortDir
     * @param pageSize
     * @return
     */

    @GetMapping("/trader")
    public ResponseEntity<GridResult> getByTraderId(@RequestParam(defaultValue = "1") Integer currentPage,
                                                   @RequestParam(defaultValue = "createdDate") String sortColumn,
                                                   @RequestParam(defaultValue = "asc") String sortDir,
                                                   @RequestParam(defaultValue = "12") Integer pageSize) {
        logger.info("Start getByTraderId - currentPage: {}, sortColumn: {}, sortDir: {}, pageSize:{}", currentPage, sortColumn, sortDir, pageSize);
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
            gridResult = requestService.findByTraderId(userId, pageable);
            gridResult.setIsSuccess(true);
            gridResult.setMessage(UserFriendlyMessage.REQUEST_GET_LIST_SUCCEED);
        } catch (BaseServiceException bse) {
            logger.error("Error in getByTraderId():", bse);
            gridResult.setIsSuccess(false);
            gridResult.setMessage(UserFriendlyMessage.REQUEST_GET_LIST_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in getByTraderId():", e);
            gridResult.setIsSuccess(false);
            gridResult.setMessage(UserFriendlyMessage.REQUEST_GET_LIST_FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End getByTraderId");
        return new ResponseEntity<>(gridResult, httpStatus);
    }

    /**
     * Api to create a request to trade items
     *
     * @param requestDTO
     * @return
     */

    @PostMapping
    public ResponseEntity<ContentResult> createRequest(@Valid @RequestBody RequestDTO requestDTO) {
        logger.info("Start createRequest - requestDTO: {}", requestDTO);
        ContentResult contentResult = new ContentResult();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Preconditions.checkNotNull(requestDTO);
            Long userId = securityUtil.getAuthenticatedUserId();
            //Ensure use logged in is same user who is creating the request to trade item
            Preconditions.checkArgument(requestDTO.getTraderId().equals(userId));
            RequestDTO created  = this.requestService.create(requestDTO);
            contentResult.setIsSuccess(true);
            contentResult.setData(created);
            contentResult.setMessage(UserFriendlyMessage.REQUEST_CREATE_SUCCEED);
        } catch (BaseServiceException bse) {
            logger.error("Error in createRequest():", bse);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.REQUEST_CREATE_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in createRequest():", e);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.REQUEST_CREATE_FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End createRequest");
        return new ResponseEntity<>(contentResult, httpStatus);
    }


    /**
     * Api to update a request to trade items
     *
     * @param requestDTO
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContentResult> updateRequest(@PathVariable Long id, @Valid @RequestBody RequestDTO requestDTO) {
        logger.info("Start updateRequest - id: {}, requestDTO: {}", id, requestDTO);
        ContentResult contentResult = new ContentResult();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Preconditions.checkNotNull(id);
            Preconditions.checkNotNull(requestDTO);
            Long userId = securityUtil.getAuthenticatedUserId();
            //Ensure use logged in is same user who is updating the request to trade item
            Preconditions.checkArgument(requestDTO.getTraderId().equals(userId));
            Preconditions.checkArgument(id.equals(requestDTO.getId()));
            RequestDTO updated  = this.requestService.update(requestDTO);
            contentResult.setIsSuccess(true);
            contentResult.setData(updated);
            contentResult.setMessage(UserFriendlyMessage.REQUEST_UPDATE_SUCCEED);
        } catch (BaseServiceException bse) {
            logger.error("Error in updateRequest():", bse);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.REQUEST_UPDATE_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in updateRequest():", e);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.REQUEST_UPDATE_FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End updateRequest");
        return new ResponseEntity<>(contentResult, httpStatus);
    }

    /**
     * Api to update the status of a request
     *
     * @param id id of the request to be updated.
     * @param status new status of request
     * @return Response to send to client.
     */
    @PutMapping("/{id}/{status}")
    public ResponseEntity<ContentResult> updateRequestStatus(@PathVariable Long id, @PathVariable String status) {
        logger.info("Start updateRequestStatus - id: {}, newStatus: {}", id, status);
        ContentResult contentResult = new ContentResult();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Preconditions.checkNotNull(id);
            Preconditions.checkNotNull(status);
            RequestDTO requestDTO = requestService.findById(id);
            Long userId = securityUtil.getAuthenticatedUserId();
            // Ensure user logged in is same user who is updating the request to trade item
            Preconditions.checkArgument(requestDTO.getTraderId().equals(userId));
            /* Update status of requestDTO */
            RequestDTO updated  = this.requestService.update(requestDTO, status);
            contentResult.setIsSuccess(true);
            contentResult.setData(updated);
            contentResult.setMessage(UserFriendlyMessage.REQUEST_UPDATE_SUCCEED);
        } catch (BaseServiceException bse) {
            logger.error("Error in updateRequestStatus():", bse);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.REQUEST_UPDATE_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in updateRequestStatus():", e);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.REQUEST_UPDATE_FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End updateRequestStatus");
        return new ResponseEntity<>(contentResult, httpStatus);
    }

    /**
     * Api to delete a request
     *
     * @param id id of the request to be deleted.
     * @return Response to send to client.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ContentResult> deleteRequest(@PathVariable Long id) {
        logger.info("Start deleteRequest - id: {}", id);
        ContentResult contentResult = new ContentResult();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Preconditions.checkNotNull(id);
            RequestDTO toDelete = requestService.findById(id);
            Long userId = securityUtil.getAuthenticatedUserId();
            //Ensure use logged in is same user who is deleting the request to trade item
            Preconditions.checkArgument(toDelete.getTraderId().equals(userId));

            RequestDTO requestDTO = this.requestService.delete(toDelete);
            contentResult.setIsSuccess(true);
            contentResult.setData(requestDTO);
            contentResult.setMessage(UserFriendlyMessage.REQUEST_DELETE_SUCCEED);
        } catch (BaseServiceException bse) {
            logger.error("Error in deleteRequest():", bse);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.REQUEST_DELETE_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in deleteRequest():", e);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.REQUEST_DELETE_FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End deleteRequest");
        return new ResponseEntity<>(contentResult, httpStatus);
    }
}
