package com.swappee.domain.like;

import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A Like entity
 */
@Entity
@Table(name = "likes")
@Where(clause = "item_deleted = false")
@EntityListeners({AuditingEntityListener.class})
public class Like implements Serializable {
    private static final long serialVersionUID = 7367372630849886871L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "item_deleted", nullable = false)
    private boolean itemDeleted;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isItemDeleted() {
        return itemDeleted;
    }

    public void setItemDeleted(boolean itemDeleted) {
        this.itemDeleted = itemDeleted;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", userId=" + userId +
                ", createdDate=" + createdDate +
                ", itemDeleted=" + itemDeleted +
                '}';
    }
}
