package com.colome.filerouge.modules.role;

import com.colome.filerouge.entity.Role;
import com.colome.filerouge.handlers.exceptionHandler.OperationException;
import com.colome.filerouge.handlers.exceptionHandler.ResourceNotFoundException;
import com.colome.filerouge.modules.role.dto.RoleRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements RoleServiceInterface {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Role with id " + id + " not found")
                );
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository
                .findByName(name)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Role with name " + name + " not found")
                );
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role addRole(RoleRequestDTO role) {
        // check if role with name already exists
        if (roleRepository.findByName(role.getRoleName()).isPresent()) {
            throw new OperationException("Role with name " + role.getRoleName() + " already exists");
        }

        // mapping roleRequestDTO to role
        Role newRole = role.toRole();

        // save role to database
        Role newRole1 =  roleRepository.save(newRole);

        return newRole1;
    }

    @Override
    public Role updateRole(RoleRequestDTO role, Long id) {
        Role existingRole = getRoleById(id);

        if(roleRepository.findByName(role.getRoleName()).isPresent() && !existingRole.getName().equals(role.getRoleName())){
            throw new OperationException("Role with name " + role.getRoleName() + " already exists");
        }

        existingRole.setName(role.getRoleName());

        return roleRepository.save(existingRole);
    }

    @Override
    public void deleteRole(Long id) {
        getRoleById(id);
        roleRepository.deleteById(id);
    }
}
