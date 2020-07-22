package com.swappee.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.time.LocalDateTime;

public class UserViewDTO {
    @JsonProperty("Id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonProperty("UserName")
    private String userName;

    @JsonProperty("AvatarPath")
    private String avatarPath;

    @JsonProperty("EmailOnly")
    private boolean emailOnly;

    @JsonProperty("Score")
    private Long score;

    @JsonProperty("TotalTraded")
    private Long totalTraded;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public boolean isEmailOnly() {
        return emailOnly;
    }

    public void setEmailOnly(boolean emailOnly) {
        this.emailOnly = emailOnly;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getTotalTraded() {
        return totalTraded;
    }

    public void setTotalTraded(Long totalTraded) {
        this.totalTraded = totalTraded;
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
        return "UserViewDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", emailOnly=" + emailOnly +
                ", score=" + score +
                ", totalTraded=" + totalTraded +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
