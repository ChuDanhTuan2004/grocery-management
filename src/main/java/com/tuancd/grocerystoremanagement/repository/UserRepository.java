package com.tuancd.grocerystoremanagement.repository;

import com.tuancd.grocerystoremanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
