package com.swappee.controller.publicapi.user;

import com.google.common.base.Preconditions;
import com.swappee.domain.user.User;
import com.swappee.model.item.ItemViewDTO;
import com.swappee.model.user.UserDTO;
import com.swappee.model.user.UserViewDTO;
import com.swappee.model.wrapper.ContentResult;
import com.swappee.model.wrapper.GridResult;
import com.swappee.service.user.UserService;
import com.swappee.utils.exception.BaseServiceException;
import com.swappee.utils.exception.UserFriendlyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Public REST controller for managing pictures.
 * Only has Get methods
 */

@RestController
@RequestMapping("/api/public/user")
public class UserPublicApiController {
    private static final Logger logger = LoggerFactory.getLogger(UserPublicApiController.class);

    @Autowired
    UserService userService;

    /**
     * Api to get user view by username
     * @param username
     * @return
     */
    @GetMapping("/{username}")
    public ResponseEntity<ContentResult> getUser(@PathVariable String username) {
        logger.info("Start getUser - username: {}", username);
        ContentResult contentResult = new ContentResult();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Preconditions.checkNotNull(username);
            contentResult.setIsSuccess(true);
            contentResult.setData(this.userService.findUserViewByUsername(username));
            contentResult.setMessage(UserFriendlyMessage.USER_GET_ONE_SUCCEED);
        } catch (BaseServiceException bse) {
            logger.error("Error in getUser():", bse);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.USER_GET_ONE_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in getUser():", e);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.USER_GET_ONE_FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End getUser");
        return new ResponseEntity<>(contentResult, httpStatus);
    }

    /**
     * Api to list User views by pageable
     * @param currentPage
     * @param sortColumn
     * @param sortDir
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<GridResult> getAll(@RequestParam(defaultValue = "1") Integer currentPage,
                                             @RequestParam(defaultValue = "createdDate") String sortColumn,
                                             @RequestParam(defaultValue = "asc") String sortDir,
                                             @RequestParam(defaultValue = "12") Integer pageSize) {
        logger.info("Start getAll - currentPage: {}, sortColumn: {}, sortDir: {}, pageSize:{}", currentPage, sortColumn, sortDir, pageSize);
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
            Page<UserViewDTO> users = userService.getAll(pageable);
            gridResult.setTotalRecordCount(users.getNumberOfElements());
            gridResult.setData(users.toList());
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

    /**
     * Api to display Avatar by username
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}/avatar")
    public ResponseEntity<ByteArrayResource> displayAvatar(@PathVariable Long id) {
        logger.info("Start displayAvatar - id: {}", id);
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Preconditions.checkArgument(id != null);
            UserDTO userDTO = this.userService.findUserById(id);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "Content-Disposition: inline; filename=\"" + userDTO.getUsername() + ".jpeg\"")
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(userDTO.getAvatar().length))
                    .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .body(new ByteArrayResource(userDTO.getAvatar()));
        } catch (BaseServiceException bse) {
            logger.error("Error in display avatar: ", bse);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception ex) {
            logger.error("Error in display avatar: ", ex);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End displayAvatar");
        return new ResponseEntity<>(httpStatus);
    }



}
