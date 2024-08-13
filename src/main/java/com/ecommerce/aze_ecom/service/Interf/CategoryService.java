package com.ecommerce.aze_ecom.service.Interf;

import com.ecommerce.aze_ecom.beans.Category;
import com.ecommerce.aze_ecom.playload.CategoryDTO;
import com.ecommerce.aze_ecom.playload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories();
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    String deleteCategory(Long categoryId);

    Category updateCategory(Category category, Long categoryId);
}
