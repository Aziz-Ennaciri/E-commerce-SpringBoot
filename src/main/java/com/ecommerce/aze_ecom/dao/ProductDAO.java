package com.ecommerce.aze_ecom.dao;

import com.ecommerce.aze_ecom.beans.Category;
import com.ecommerce.aze_ecom.beans.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends JpaRepository<Product,Long> {
    Page<Product> findByCategoryOrderByPriceAsc(Category category, Pageable pageableDetails);

    Page<Product> findByProductNameLikeIgnoreCase(String keyword, Pageable pageableDetails);
}
