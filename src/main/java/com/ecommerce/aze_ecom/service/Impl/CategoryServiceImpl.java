package com.ecommerce.aze_ecom.service.Impl;

import com.ecommerce.aze_ecom.beans.Category;
import com.ecommerce.aze_ecom.repositories.CategoryRepository;
import com.ecommerce.aze_ecom.exceptions.APIException;
import com.ecommerce.aze_ecom.exceptions.ResourceNotFoundException;
import com.ecommerce.aze_ecom.mappers.CategoryMapper;
import com.ecommerce.aze_ecom.playload.CategoryDTO;
import com.ecommerce.aze_ecom.playload.CategoryResponse;
import com.ecommerce.aze_ecom.service.Interf.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
//    @Autowired
//    private ModelMapper modelMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy , String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")?
                Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageableDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageableDetails);
        List<Category> categories = categoryPage.getContent();
        if (categories.isEmpty())
            throw new APIException("There's no category created");
        List<CategoryDTO> categoryDTOs = categories.stream()
                .map(categoryMapper::toDto)
                .toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOs);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());
        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
//        Category savedCategory = categoryDao.findByCategoryName(category.getCategoryName());
//        if (savedCategory != null)
//            throw new APIException("Category with name" + category.getCategoryName() + "it's already exist");
//        categoryDao.save(category);
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        CategoryDTO categoryDTO = categoryMapper.toDto(savedCategory);
        categoryRepository.delete(savedCategory);
        return categoryDTO;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO ,Long categoryId) {
//        method 1
//        Category savedCategory = categoryDao.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
//        category.setCategoryId(categoryId);
//        savedCategory=categoryDao.save(category);
//        return savedCategory;
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        // Update the existing category's fields with the data from the provided DTO
        existingCategory.setCategoryName(categoryDTO.getCategoryName());

        // Save the updated category
        Category updatedCategory = categoryRepository.save(existingCategory);

        // Convert the updated entity back to a DTO and return it
        return categoryMapper.toDto(updatedCategory);




//        this method 2
//        List<Category> categories = categoryDao.findAll();
//        Optional<Category> optionalCategory = categories.stream()
//                .filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst();
//        if (optionalCategory.isPresent()){
//            Category existingCategory = optionalCategory.get();
//            existingCategory.setCategoryName(category.getCategoryName());
//            return categoryDao.save(existingCategory);
//        }
//        else{
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
    }
}
