package com.colome.filerouge.modules.role;

import com.colome.filerouge.entity.Role;
import com.colome.filerouge.modules.role.dto.RoleRequestDTO;

import java.util.List;

public interface RoleServiceInterface {
    Role getRoleById(Long id);
    Role getRoleByName(String name);
    List<Role> getAllRoles();
    Role addRole(RoleRequestDTO role);
    Role updateRole(RoleRequestDTO role, Long id);
    void deleteRole(Long id);
}
