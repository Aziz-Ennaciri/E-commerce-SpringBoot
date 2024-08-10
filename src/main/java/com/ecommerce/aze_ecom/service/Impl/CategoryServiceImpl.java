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
        List<Category> categories = categoryDao.findAll();
        Category category = categories.stream()
                .filter(c->c.getCategoryId().equals(categoryId)).findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (category==null)
            return "Category not found";


        categoryDao.delete(category);
        return "the category"+categoryId+"has been deleted";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        List<Category> categories = categoryDao.findAll();
        Optional<Category> optionalCategory = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();
        if (optionalCategory.isPresent()){
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            return categoryDao.save(existingCategory);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
