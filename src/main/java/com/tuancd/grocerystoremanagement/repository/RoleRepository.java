package com.tuancd.grocerystoremanagement.repository;

import com.tuancd.grocerystoremanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleName);
}
