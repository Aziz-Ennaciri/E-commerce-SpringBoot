package com.ecommerce.aze_ecom.dao;

import com.ecommerce.aze_ecom.beans.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends JpaRepository<Product,Long> {
}
