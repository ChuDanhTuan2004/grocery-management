package com.tuancd.grocerystoremanagement.repository;

import com.tuancd.grocerystoremanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
