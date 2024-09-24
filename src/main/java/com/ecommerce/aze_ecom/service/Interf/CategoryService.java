package com.ecommerce.aze_ecom.service.Interf;

import com.ecommerce.aze_ecom.DTOs.CategoryDTO;
import com.ecommerce.aze_ecom.playload.CategoryResponse;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber , Integer pageSize , String sortBy , String sortOrder);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
