package com.swappee.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@XmlRootElement
public class UserViewDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String username;

    private String avatarPath;

    private boolean emailOnly;

    private Long score;

    private Long totalTraded;

    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDateTime lastModifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                ", username='" + username + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", emailOnly=" + emailOnly +
                ", score=" + score +
                ", totalTraded=" + totalTraded +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
