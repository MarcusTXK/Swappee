package com.swappee.controller.publicapi.mail;

import com.google.common.base.Preconditions;
import com.swappee.controller.publicapi.item.ItemPublicApiController;
import com.swappee.model.wrapper.ContentResult;
import com.swappee.utils.exception.UserFriendlyMessage;
import com.swappee.service.mail.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.SendFailedException;

/**
 * Public REST controller for sending emails.
 *
 */

@RestController
@RequestMapping("/api/public/mail")
public class MailPublicApiController {

    public static String DEFAULT_SUBJECT = "Hello from Swappee!";
    public static String DEFAULT_TEXT = "Welcome to Swappee!";

    private static final Logger logger = LoggerFactory.getLogger(ItemPublicApiController.class);
    @Autowired
    EmailService emailService;

    @PostMapping("/reset-password")
    public ResponseEntity<ContentResult> createEmailRequest(@RequestParam("to") String to) {
        logger.info("Start createEmailRequest - to: {}", to);
        ContentResult contentResult = new ContentResult();
        contentResult.setIsSuccess(true);
        contentResult.setMessage(UserFriendlyMessage.EMAIL_SEND_SUCCEED);
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Preconditions.checkNotNull(to);
            emailService.sendEmail(to, DEFAULT_SUBJECT, DEFAULT_TEXT);
        } catch (SendFailedException sfe) {
            logger.error("Error in sendEmail():", sfe);
            contentResult.setIsSuccess(false);
            contentResult.setMessage(UserFriendlyMessage.EMAIL_SEND_FAILED);
            httpStatus = HttpStatus.FAILED_DEPENDENCY;
        }
        logger.info("End createEmailRequest");
        return new ResponseEntity<>(contentResult, httpStatus);
    }

}
