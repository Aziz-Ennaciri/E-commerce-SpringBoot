package com.ecommerce.aze_ecom.service.Interf;

import com.ecommerce.aze_ecom.beans.Category;
import com.ecommerce.aze_ecom.playload.CategoryDTO;
import com.ecommerce.aze_ecom.playload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber , Integer pageSize);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
