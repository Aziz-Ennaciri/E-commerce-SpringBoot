package com.ecommerce.aze_ecom.mappers;

import com.ecommerce.aze_ecom.beans.Category;
import com.ecommerce.aze_ecom.DTOs.CategoryDTO;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryId(categoryDTO.getCategoryId());
        category.setCategoryName(categoryDTO.getCategoryName());
        return category;
    }

    public CategoryDTO toDto(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getCategoryId());
        categoryDTO.setCategoryName(category.getCategoryName());
        return categoryDTO;
    }
}
