package com.ecommerce.aze_ecom.dao;

import com.ecommerce.aze_ecom.beans.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category,Long> {
    Category findByCategoryName(String categoryName);
}
