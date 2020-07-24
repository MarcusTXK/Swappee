package com.swappee.model.picture;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class PictureDTO implements Serializable {
    private static final long serialVersionUID = 5862364792522666395L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Long itemId;

    private Long order;

    private byte[] fileData;

    private String fileName;

    private String contentType;

    private Long contentLength;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getContentLength() {
        return contentLength;
    }

    public void setContentLength(Long contentLength) {
        this.contentLength = contentLength;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PictureDTO{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", order=" + order +
                ", fileData=" + fileData.length +
                ", fileName='" + fileName + '\'' +
                ", contentType='" + contentType + '\'' +
                ", contentLength=" + contentLength +
                ", description='" + description + '\'' +
                '}';
    }
}
