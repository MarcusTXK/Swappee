package com.swappee.domain.item;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * A PreferredItem embeddable
 */
@Embeddable
public class PreferredItem implements Serializable {
    private static final long serialVersionUID = -4918246115319232066L;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "category", length = 200, nullable = false)
    private String category;

    @Column(name = "brand", length = 200, nullable = false)
    private String brand;

    @Column(name = "new", nullable = false)
    private boolean isNew;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    @Override
    public String toString() {
        return "PreferredItem{" +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", isNew=" + isNew +
                '}';
    }
}
