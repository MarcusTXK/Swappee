package com.swappee.model.item;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Preferred Item DTO
 */

@XmlRootElement
public class PreferredItemDTO {
    private String name;

    private String category;

    private String brand;

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
        return "PreferredItemDTO{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", isNew=" + isNew +
                '}';
    }
}