package com.ecommerce.aze_ecom.repositories;

import com.ecommerce.aze_ecom.beans.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
