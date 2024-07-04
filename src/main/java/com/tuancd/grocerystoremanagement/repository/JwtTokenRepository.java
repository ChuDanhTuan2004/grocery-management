package com.tuancd.grocerystoremanagement.repository;

import com.tuancd.grocerystoremanagement.model.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
    JwtToken findByTokenEquals(String token);
}
