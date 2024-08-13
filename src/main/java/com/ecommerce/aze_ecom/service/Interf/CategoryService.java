package com.ecommerce.aze_ecom.service.Interf;

import com.ecommerce.aze_ecom.beans.Category;
import com.ecommerce.aze_ecom.playload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories();
    void createCategory(Category category);
    String deleteCategory(Long categoryId);

    Category updateCategory(Category category, Long categoryId);
}
