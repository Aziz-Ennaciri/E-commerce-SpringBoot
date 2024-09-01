package com.ecommerce.aze_ecom.repositories;

import com.ecommerce.aze_ecom.beans.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
