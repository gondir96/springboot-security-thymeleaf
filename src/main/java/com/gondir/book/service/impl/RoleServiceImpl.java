package com.gondir.book.service.impl;

import com.gondir.book.entity.Role;
import com.gondir.book.repository.RoleRepository;
import com.gondir.book.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteRoleById(Long roleId) {
        roleRepository.deleteById(roleId);
    }
}
