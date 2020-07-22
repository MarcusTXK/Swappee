package com.swappee.model.picture;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class PictureViewDTO {
    @JsonProperty("Id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonProperty("Order")
    private Long order;

    @JsonProperty("ImagePath")
    private String ImagePath;

    @JsonProperty("Description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PictureViewDTO{" +
                "id=" + id +
                ", order=" + order +
                ", ImagePath='" + ImagePath + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
