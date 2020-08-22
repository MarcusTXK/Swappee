package com.swappee.controller.privateapi.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.swappee.model.user.UserDTO;
import com.swappee.model.wrapper.ContentResult;
import com.swappee.service.user.UserService;
import com.swappee.utils.exception.BaseServiceException;
import com.swappee.utils.exception.UserFriendlyMessage;
import com.swappee.utils.picture.PictureUtil;
import com.swappee.utils.security.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * REST controller for managing users
 */
@RestController
@RequestMapping("/api/private/user")
public class UserPrivateApiController {
    private static final Logger logger = LoggerFactory.getLogger(UserPrivateApiController.class);

    @Autowired
    UserService userService;

    @Autowired
    SecurityUtil securityUtil;

    @Autowired
    PictureUtil pictureUtil;

    /**
     * Api to get user by id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContentResult> getUser(@PathVariable Long id) {
        logger.info("Start getUser - id: {}", id);
        ContentResult contentResult = new ContentResult();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Preconditions.checkNotNull(id);
            Long userId = securityUtil.getAuthenticatedUserId();
            //Ensure user logged in is fetching his own information
            Preconditions.checkArgument(id.equals(userId));
            UserDTO userDTO = this.userService.findUserById(id);
            contentResult.setIsSuccess(true);
            contentResult.setData(userDTO);
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


//    /**
//     * Api to update user
//     *
//     * @param jsonString
//     * @param avatar
//     * @return
//     * @throws JsonProcessingException
//     */
//    @PutMapping(value = "/{id}" ,consumes = {"multipart/form-data"})
//    public ResponseEntity<ContentResult> createUser(@RequestParam("itemDTO") String jsonString, @RequestParam("avatar") MultipartFile avatar) throws JsonProcessingException {
//        logger.info("Start createItem - userDTO: {}, photo: {}", jsonString, avatar);
//        ContentResult contentResult = new ContentResult();
//        HttpStatus httpStatus = HttpStatus.OK;
//        try {
//            Preconditions.checkNotNull(jsonString);
//            Preconditions.checkNotNull(avatar);
//
//            UserDTO userDTO = new ObjectMapper().readValue(jsonString, UserDTO.class);
//            //resize img and convert to jpg to reduce size while minimising image quality loss
//            userDTO.setAvatar(pictureUtil.processImagetoByteArray(avatar));
//            UserDTO createdUserDTO = userService.create(userDTO);
//            contentResult.setIsSuccess(true);
//            contentResult.setData(createdUserDTO);
//            contentResult.setMessage(UserFriendlyMessage.USER_CREATE_SUCCEED);
//        } catch (BaseServiceException bse) {
//            logger.error("Error in createUser():", bse);
//            contentResult.setIsSuccess(false);
//            contentResult.setMessage(UserFriendlyMessage.USER_CREATE_FAILED);
//            httpStatus = HttpStatus.NOT_FOUND;
//        } catch (Exception e) {
//            logger.error("Error in createUser():", e);
//            contentResult.setIsSuccess(false);
//            contentResult.setMessage(UserFriendlyMessage.USER_CREATE_FAILED);
//            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        logger.info("End createUser");
//        return new ResponseEntity<>(contentResult, httpStatus);
//    }



}
