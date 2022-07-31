package com.swappee.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.swappee.domain.request.Request;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDateTime;

@XmlRootElement
public class RequestDTO implements Serializable {
    private static final long serialVersionUID = -8469389039930792658L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Long ownerId;

    private Long traderId;

    private Long ownerItemId;

    private Long traderItemId;

    private String status;

    private String remarks;

    private boolean ownerReviewed;

    private boolean traderReviewed;

    private boolean ownerHidden;

    private boolean traderHidden;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    private Long version;

    private boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getOwnerItemId() {
        return ownerItemId;
    }

    public void setOwnerItemId(Long ownerItemId) {
        this.ownerItemId = ownerItemId;
    }

    public Long getTraderItemId() {
        return traderItemId;
    }

    public void setTraderItemId(Long traderItemId) {
        this.traderItemId = traderItemId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isOwnerReviewed() {
        return ownerReviewed;
    }

    public void setOwnerReviewed(boolean ownerReviewed) {
        this.ownerReviewed = ownerReviewed;
    }

    public boolean isTraderReviewed() {
        return traderReviewed;
    }

    public void setTraderReviewed(boolean traderReviewed) {
        this.traderReviewed = traderReviewed;
    }

    public boolean isOwnerHidden() {
        return ownerHidden;
    }

    public void setOwnerHidden(boolean ownerHidden) {
        this.ownerHidden = ownerHidden;
    }

    public boolean isTraderHidden() {
        return traderHidden;
    }

    public void setTraderHidden(boolean traderHidden) {
        this.traderHidden = traderHidden;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", traderId=" + traderId +
                ", ownerItemId=" + ownerItemId +
                ", traderItemId=" + traderItemId +
                ", status='" + status + '\'' +
                ", remarks='" + remarks + '\'' +
                ", ownerReviewed=" + ownerReviewed +
                ", traderReviewed=" + traderReviewed +
                ", ownerHidden=" + ownerHidden +
                ", traderHidden=" + traderHidden +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
