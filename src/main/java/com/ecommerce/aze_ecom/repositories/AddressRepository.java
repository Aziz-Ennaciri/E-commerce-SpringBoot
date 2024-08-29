package com.ecommerce.aze_ecom.repositories;

import com.ecommerce.aze_ecom.beans.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
