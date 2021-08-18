package com.swappee.controller.publicapi.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.swappee.model.user.UserDTO;
import com.swappee.model.user.UserViewDTO;
import com.swappee.model.wrapper.ContentResult;
import com.swappee.model.wrapper.GridResult;
import com.swappee.service.user.UserService;
import com.swappee.utils.exception.BaseServiceException;
import com.swappee.utils.exception.UserFriendlyMessage;
import com.swappee.utils.picture.PictureUtil;
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
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    PictureUtil pictureUtil;

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
            Preconditions.checkNotNull(id);
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

    /**
     * Api to create user
     *
     * @param jsonString
     * @param avatar
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ContentResult> createUser(@RequestParam("userDTO") String jsonString, @RequestParam("avatar") MultipartFile avatar) throws JsonProcessingException {
        logger.info("Start createItem - userDTO: {}, photo: {}", jsonString, avatar);
        ContentResult contentResult = new ContentResult();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Preconditions.checkNotNull(jsonString);
            Preconditions.checkNotNull(avatar);

            UserDTO userDTO = new ObjectMapper().readValue(jsonString, UserDTO.class);
            //resize img and convert to jpg to reduce size while minimising image quality loss
            userDTO.setAvatar(pictureUtil.processImagetoByteArray(avatar));
            UserDTO createdUserDTO = userService.create(userDTO);
            contentResult.setIsSuccess(true);
            contentResult.setData(createdUserDTO);
            contentResult.setMessage(UserFriendlyMessage.USER_CREATE_SUCCEED);
        } catch (BaseServiceException bse) {
            logger.error("Error in createUser():", bse);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.USER_CREATE_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error("Error in createUser():", e);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.USER_CREATE_FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("End createUser");
        return new ResponseEntity<>(contentResult, httpStatus);
    }



}
