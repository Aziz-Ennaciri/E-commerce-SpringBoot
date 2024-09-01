package com.ecommerce.aze_ecom.repositories;

import com.ecommerce.aze_ecom.beans.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
