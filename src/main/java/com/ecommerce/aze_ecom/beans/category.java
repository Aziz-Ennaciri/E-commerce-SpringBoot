package com.ecommerce.aze_ecom.beans;

public class category {
    private long categoryId;
    private long categoryName;

    public category() {
    }

    public category(long categoryId, long categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(long categoryName) {
        this.categoryName = categoryName;
    }
}
