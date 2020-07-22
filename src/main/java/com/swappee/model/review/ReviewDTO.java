package com.swappee.model.review;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;

public class ReviewDTO {
    @JsonProperty("Id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonProperty("RequestId")
    private Long requestId;

    @JsonProperty("OwnerId")
    private Long ownerId;

    @JsonProperty("TraderId")
    private Long traderId;

    @JsonProperty("Score")
    private Long score;

    @JsonProperty("Remarks")
    private String remarks;

    @JsonProperty("CreatedDate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getTraderId() {
        return traderId;
    }

    public void setTraderId(Long traderId) {
        this.traderId = traderId;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "id=" + id +
                ", requestId=" + requestId +
                ", ownerId=" + ownerId +
                ", traderId=" + traderId +
                ", score=" + score +
                ", remarks='" + remarks + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
