package com.tuancd.grocerystoremanagement.service;


import com.tuancd.grocerystoremanagement.model.Role;

public interface RoleService {
    Iterable<Role> findAll();

    void save(Role role);

    Role findByName(String name);
}