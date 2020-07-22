package com.swappee.model.items;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.swappee.model.picture.PictureViewDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Item DTO with pictures, user's Id and whether or not its been liked by logged in user
 * Used for the front end, when displaying it alone
 * User's info will be fetched separately
 */
public class ItemViewDTO {
    @JsonProperty("Id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonProperty("ImagePath")
    private String imagePath;

    @JsonProperty("UserId")
    private Long userId;

    @JsonProperty("Status")
    private String status;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("Brand")
    private String brand;

    @JsonProperty("IsNew")
    private boolean isNew;

    @JsonProperty("Category")
    private String category;

    @JsonProperty("Strict")
    private boolean strict;

    @JsonProperty("Likes")
    private Long likes;

    @JsonProperty("PictureViews")
    private List<PictureViewDTO> pictureViews;

    @JsonProperty("PreferredCats")
    private List<String> preferredCats;

    @JsonProperty("PreferredItems")
    private List<PreferredItemDTO> preferredItems;

    @JsonProperty("CreatedDate")
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDateTime createdDate;

    @JsonProperty("LastModifiedDate")
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDateTime lastModifiedDate;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isStrict() {
        return strict;
    }

    public void setStrict(boolean strict) {
        this.strict = strict;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public List<PictureViewDTO> getPictureViews() {
        return pictureViews;
    }

    public void setPictureViews(List<PictureViewDTO> pictureViews) {
        this.pictureViews = pictureViews;
    }

    public List<String> getPreferredCats() {
        return preferredCats;
    }

    public void setPreferredCats(List<String> preferredCats) {
        this.preferredCats = preferredCats;
    }

    public List<PreferredItemDTO> getPreferredItems() {
        return preferredItems;
    }

    public void setPreferredItems(List<PreferredItemDTO> preferredItems) {
        this.preferredItems = preferredItems;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public String toString() {
        return "ItemViewDTO{" +
                "id=" + id +
                ", imagePath='" + imagePath + '\'' +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", isNew=" + isNew +
                ", category='" + category + '\'' +
                ", strict=" + strict +
                ", likes=" + likes +
                ", pictureViews=" + pictureViews +
                ", preferredCats=" + preferredCats +
                ", preferredItems=" + preferredItems +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
