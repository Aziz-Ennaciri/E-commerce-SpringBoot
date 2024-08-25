package com.ecommerce.aze_ecom.repositories;

import com.ecommerce.aze_ecom.Enums.AppRole;
import com.ecommerce.aze_ecom.beans.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}
