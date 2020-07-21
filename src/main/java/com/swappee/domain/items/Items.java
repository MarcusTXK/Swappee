package com.swappee.domain.items;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * An Items entity
 */
@Entity
@Table(name = "items")
@Where(clause = "deleted = false")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50, nullable = false)
    private Status status;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "brand", length = 200, nullable = false)
    private String brand;

    @Column(name = "new", nullable = false)
    private boolean isNew;

    @Column(name = "category", length = 200, nullable = false)
    private String category;

    @Column(name = "strict", nullable = false)
    private boolean strict;

    @Column(name = "likes", nullable = false)
    private Long likes;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "preferred_cats", joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"))
    @Fetch(FetchMode.SELECT)
    @Column(name = "category")
    private List<String> preferredCats = new LinkedList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "preferred_items", joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"))
    @Fetch(FetchMode.SELECT)
    private List<PreferredItems> preferredItems = new LinkedList<>();

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

    public enum Status {
        OPEN,
        RESERVED,
        TRADED
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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

    public List<String> getPreferredCats() {
        return preferredCats;
    }

    public void setPreferredCats(List<String> preferredCats) {
        this.preferredCats = preferredCats;
    }

    public List<PreferredItems> getPreferredItems() {
        return preferredItems;
    }

    public void setPreferredItems(List<PreferredItems> preferredItems) {
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
        return "Items{" +
                "id=" + id +
                ", userId=" + userId +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", isNew=" + isNew +
                ", category='" + category + '\'' +
                ", strict=" + strict +
                ", likes=" + likes +
                ", preferredCats=" + preferredCats +
                ", preferredItems=" + preferredItems +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", version=" + version +
                ", deleted=" + deleted +
                '}';
    }
}
