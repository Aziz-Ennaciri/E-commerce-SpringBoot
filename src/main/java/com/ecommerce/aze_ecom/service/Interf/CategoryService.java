package com.ecommerce.aze_ecom.service.Interf;

import com.ecommerce.aze_ecom.beans.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    void createCategory(Category category);
    String deleteCategory(Long categoryId);
}
