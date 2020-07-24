package com.swappee.domain.review;

import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A Review entity
 */
@Entity
@Table(name = "review")
@Where(clause = "deleted = false")
public class Review implements Serializable {
    private static final long serialVersionUID = -1328746078364898334L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_id", nullable = false)
    private Long requestId;

    @Column(name = "reviewer_id", nullable = false)
    private Long reviewerId;

    @Column(name = "reviewed_id", nullable = false)
    private Long reviewedId;

    @Column(name = "score", nullable = false)
    private Long score;

    @Lob
    @Column(name = "remarks", nullable = false)
    private String remarks;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

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

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public Long getReviewedId() {
        return reviewedId;
    }

    public void setReviewedId(Long reviewedId) {
        this.reviewedId = reviewedId;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", requestId=" + requestId +
                ", reviewerId=" + reviewerId +
                ", reviewedId=" + reviewedId +
                ", score=" + score +
                ", remarks='" + remarks + '\'' +
                ", createdDate=" + createdDate +
                ", deleted=" + deleted +
                '}';
    }
}
