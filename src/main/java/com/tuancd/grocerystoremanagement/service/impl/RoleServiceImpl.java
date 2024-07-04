package com.tuancd.grocerystoremanagement.service.impl;

import com.tuancd.grocerystoremanagement.model.Role;
import com.tuancd.grocerystoremanagement.repository.RoleRepository;
import com.tuancd.grocerystoremanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRespository;

    @Override
    public Iterable<Role> findAll() {
        return roleRespository.findAll();
    }

    @Override
    public void save(Role role) {
        roleRespository.save(role);
    }

    @Override
    public Role findByName(String name) {
        return roleRespository.findByName(name);
    }
}