package com.ecommerce.aze_ecom.repositories;

import com.ecommerce.aze_ecom.beans.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}