package com.swappee.controller.securityapi;

import com.google.common.base.Preconditions;
import com.swappee.controller.publicapi.item.ItemPublicApiController;
import com.swappee.model.user.UserDTO;
import com.swappee.model.wrapper.ContentResult;
import com.swappee.service.mail.EmailService;
import com.swappee.service.user.UserService;
import com.swappee.utils.exception.BaseServiceException;
import com.swappee.utils.exception.UserFriendlyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.mail.SendFailedException;
import java.util.UUID;

@RestController
@RequestMapping("/api/public/password")
public class PasswordController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(ItemPublicApiController.class);

    @Value("${frontend.baseurl}")
    private String baseUrl;

    @PostMapping("/reset")
    public ResponseEntity<ContentResult> resetPasswordRequest(@RequestParam("email") String emailAddress) {
        logger.info("Start resetPasswordRequest - from: {}", emailAddress);
        ContentResult contentResult = new ContentResult();
        contentResult.setIsSuccess(true);
        contentResult.setMessage(UserFriendlyMessage.EMAIL_SEND_SUCCEED);
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Preconditions.checkNotNull(emailAddress);
            UserDTO user = userService.findByEmail(emailAddress);
            user.setResetToken(UUID.randomUUID().toString());
            userService.update(user);
            UserDTO userAfterSetToken = userService.findByEmail(emailAddress);
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("hello@swappee.org");
            passwordResetEmail.setTo(userAfterSetToken.getEmail());

            passwordResetEmail.setSubject("Swappee: Password Reset");
            String url = baseUrl + "/reset_password?token=" + userAfterSetToken.getResetToken();
            passwordResetEmail.setText("To reset your password, click the link below:\n" + url);
            emailService.sendEmail(passwordResetEmail);
        } catch (BaseServiceException bse) {
            logger.error("Error in findByEmail():", bse);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.USER_GET_ONE_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (SendFailedException sfe) {
            logger.error("Error in sendEmail():", sfe);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.EMAIL_SEND_FAILED);
            httpStatus = HttpStatus.FAILED_DEPENDENCY;
        }
        return new ResponseEntity<>(contentResult, httpStatus);
    }

    @PostMapping("/reset_password")
    public ResponseEntity<ContentResult> resetPassword(@RequestParam("token") String token, @RequestParam("password") String newPassword) {
        logger.info("Start resetPassword - resetToken: {}", token);
        ContentResult contentResult = new ContentResult();
        contentResult.setIsSuccess(true);
        contentResult.setMessage(UserFriendlyMessage.PASSWORD_RESET_SUCCEED);
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Preconditions.checkNotNull(token);
            Preconditions.checkNotNull(newPassword);
            UserDTO user = userService.findByResetToken(token);
            user.setPassword(newPassword);
            user.setResetToken(null);
            userService.update(user);
        } catch (BaseServiceException bse) {
            logger.error("Error in findByResetToken():", bse);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.USER_GET_ONE_FAILED);
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(contentResult, httpStatus);
    }
}