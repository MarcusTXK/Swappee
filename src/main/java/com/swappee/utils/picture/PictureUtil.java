package com.swappee.utils.picture;

import com.swappee.model.picture.PictureDTO;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

/**
 * Used to convert image to jpg and decrease the size of an image
 */
@Component
public class PictureUtil {
    private final Logger logger = LoggerFactory.getLogger(PictureUtil.class);


    public PictureDTO processImagetoPictureDTO(MultipartFile multipartFile) {
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
        } catch (IOException e) {
            e.printStackTrace();
            //TODO throw exception
            return null;
        }
    }
}
