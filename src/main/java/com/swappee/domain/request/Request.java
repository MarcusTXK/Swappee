package com.swappee.domain.request;

import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A Request entity
 */
@Entity
@Table(name = "request")
@Where(clause = "deleted = false")
public class Request implements Serializable {
    private static final long serialVersionUID = -5488686472614498358L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "trader_id", nullable = false)
    private Long traderId;

    @Column(name = "owner_item_id", nullable = false)
    private Long ownerItemId;

    @Column(name = "trader_item_id", nullable = false)
    private Long traderItemId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50, nullable = false)
    private Status status;

    @Lob
    @Column(name = "remarks")
    private String remarks;

    @Column(name = "owner_reviewed", nullable = false)
    private boolean ownerReviewed;

    @Column(name = "trader_reviewed", nullable = false)
    private boolean traderReviewed;

    @Column(name = "owner_hidden", nullable = false)
    private boolean ownerHidden;

    @Column(name = "trader_hidden", nullable = false)
    private boolean traderHidden;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate = LocalDateTime.now();

    @Version
    @Column(name = "version", nullable = false)
    private Long version = 0L;

    @Column(name = "deleted", nullable = false)
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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
        return "Request{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", traderId=" + traderId +
                ", ownerItemId=" + ownerItemId +
                ", traderItemId=" + traderItemId +
                ", status=" + status +
                ", remarks='" + remarks + '\'' +
                ", ownerReviewed=" + ownerReviewed +
                ", traderReviewed=" + traderReviewed +
                ", ownerHidden=" + ownerHidden +
                ", traderHidden=" + traderHidden +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", version=" + version +
                ", deleted=" + deleted +
                '}';
    }

    public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED,
        TRADED,
        CANCELLED,
        REMOVED
    }
}
