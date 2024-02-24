package com.store.project.service;

import com.store.project.model.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {

    Role saveRole(Role role);

    Optional<Role> getRoleById(Integer id);

    List<Role> getAllRoles();

    Role updateRole(Role role);

    void deleteRoleById(Integer id);
}
