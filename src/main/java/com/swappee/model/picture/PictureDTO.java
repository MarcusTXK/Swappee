package com.swappee.model.picture;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Arrays;

public class PictureDTO {
    @JsonProperty("Id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonProperty("ItemId")
    private Long itemId;

    @JsonProperty("Order")
    private Long order;

    @JsonProperty("FileData")
    private byte[] fileData;

    @JsonProperty("FileName")
    private String fileName;

    @JsonProperty("ContentType")
    private String contentType;

    @JsonProperty("ContentLength")
    private Long contentLength;

    @JsonProperty("Description")
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
                ", fileData=" + Arrays.toString(fileData) +
                ", fileName='" + fileName + '\'' +
                ", contentType='" + contentType + '\'' +
                ", contentLength=" + contentLength +
                ", description='" + description + '\'' +
                '}';
    }
}
