package com.gondir.book.service;

import com.gondir.book.entity.Role;


import java.util.List;

public interface RoleService {

    List<Role> findAllRole();

    void deleteRoleById(Long roleId);
}
