package com.swappee.utils.picture;

import com.swappee.model.picture.PictureDTO;
import com.swappee.utils.exception.BaseServiceException;
import com.swappee.utils.exception.ErrorMessage;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
/**
 * Used to convert image to jpg and decrease the size of an image
 */
@Component
public class PictureUtil {
    private final Logger logger = LoggerFactory.getLogger(PictureUtil.class);


    public PictureDTO processImagetoPictureDTO(MultipartFile multipartFile) throws BaseServiceException {
        try {
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());
            PictureDTO pictureDTO = new PictureDTO();
            BufferedImage scaledImage = Scalr.resize(image, 1280, 960);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(scaledImage, "jpg", byteArrayOutputStream);
            byte[] byteArrayImage = byteArrayOutputStream.toByteArray();
            pictureDTO.setFileData(byteArrayImage);
            pictureDTO.setContentType("image/jpeg");
            pictureDTO.setContentLength((long) byteArrayImage.length);
            return pictureDTO;
        } catch (Exception ex) {
            logger.error("Error in processImagetoPictureDTO(), multipartFile: {}", multipartFile);
            throw new BaseServiceException(ErrorMessage.PICTURE_UTIL_ERROR_PROCESS_FAILED, ex);
        }
    }

    public byte[] processImagetoByteArray(MultipartFile multipartFile) throws BaseServiceException {
        try {
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());
            PictureDTO pictureDTO = new PictureDTO();
            BufferedImage scaledImage = Scalr.resize(image, 1280, 960);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(scaledImage, "jpg", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception ex) {
            logger.error("Error in processImagetoByteArray(), multipartFile: {}", multipartFile);
            throw new BaseServiceException(ErrorMessage.PICTURE_UTIL_ERROR_PROCESS_FAILED, ex);
        }
    }
}
