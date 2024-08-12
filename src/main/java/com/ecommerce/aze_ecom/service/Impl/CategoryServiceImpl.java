package com.ecommerce.aze_ecom.service.Impl;

import com.ecommerce.aze_ecom.beans.Category;
import com.ecommerce.aze_ecom.dao.CategoryDao;
import com.ecommerce.aze_ecom.exceptions.APIException;
import com.ecommerce.aze_ecom.exceptions.ResourceNotFoundException;
import com.ecommerce.aze_ecom.service.Interf.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    private long nextId = 1L;
    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryDao.findAll();
        if (categories.isEmpty())
            throw new APIException("there's no category created");
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryDao.findByCategoryName(category.getCategoryName());
        if (savedCategory != null)
            throw new APIException("Category with name" + category.getCategoryName() + "it's already exist");
        category.setCategoryId(nextId++);
        categoryDao.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category savedCategory = categoryDao.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
                categoryDao.delete(savedCategory);
        return "the category"+categoryId+"has been deleted";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
//        method 1
        Category savedCategory = categoryDao.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        category.setCategoryId(categoryId);
        savedCategory=categoryDao.save(category);
        return savedCategory;




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
