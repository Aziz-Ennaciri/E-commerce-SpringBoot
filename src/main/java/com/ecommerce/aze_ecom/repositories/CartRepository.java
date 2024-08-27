package com.ecommerce.aze_ecom.repositories;

import com.ecommerce.aze_ecom.beans.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
}
