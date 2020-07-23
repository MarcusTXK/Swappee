package com.swappee.model.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

/**
 * Item DTO with cover picture, user's name and avatar and whether or not its been liked by logged in user
 * Used for the front end, when displaying in a grid
 */
@XmlRootElement
public class ItemCardDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String imagePath;

    private String name;

    private String status;

    private String brand;

    private String description;

    private boolean isNew;

    private Long likes;

    private boolean liked;

    private String userName;

    private String avatarPath;

    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDateTime createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "ItemCardDTO{" +
                "id=" + id +
                ", imagePath='" + imagePath + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", isNew=" + isNew +
                ", likes=" + likes +
                ", liked=" + liked +
                ", createdDate=" + createdDate +
                '}';
    }
}