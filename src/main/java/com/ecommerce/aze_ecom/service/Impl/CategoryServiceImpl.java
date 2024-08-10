package com.ecommerce.aze_ecom.service.Impl;

import com.ecommerce.aze_ecom.beans.Category;
import com.ecommerce.aze_ecom.dao.CategoryDao;
import com.ecommerce.aze_ecom.service.Interf.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    private long nextId = 1L;
    @Override
    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categoryDao.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category savedCategory = categoryDao.findById(categoryId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
                categoryDao.delete(savedCategory);
        return "the category"+categoryId+"has been deleted";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
//        method 1
        Category savedCategory = categoryDao.findById(categoryId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
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
