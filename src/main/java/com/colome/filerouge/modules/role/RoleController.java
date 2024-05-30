package com.colome.filerouge.modules.role;

import com.colome.filerouge.entity.Role;
import com.colome.filerouge.handlers.response.ResponseMessage;
import com.colome.filerouge.modules.role.dto.RoleRequestDTO;
import com.colome.filerouge.modules.role.dto.RoleResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleController {
    private final RoleServiceInterface roleService;

    public RoleController(RoleServiceInterface roleService) {
        this.roleService = roleService;
    }

    // get all roles
    @GetMapping()
    public ResponseEntity<?> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        if(roles.isEmpty()) {
            return ResponseMessage.notFound("No role found");
        }else {
            List<RoleResponseDTO> roleResponseDTOS = new ArrayList<>();
            for(Role role : roles) {
                roleResponseDTOS.add(RoleResponseDTO.fromRole(role));
            }
            
            return ResponseMessage.ok(roleResponseDTOS, "Success");
        }
    }

    // get role by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        return ResponseMessage.ok(RoleResponseDTO.fromRole(roleService.getRoleById(id)), "Success");
    }

    // add role
    @PostMapping()
    public ResponseEntity<?> addRole(@RequestBody RoleRequestDTO role) {
        return ResponseMessage.created(roleService.addRole(role), "Role created Successfully");
    }

    // update role
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@RequestBody RoleRequestDTO role, @PathVariable Long id) {
        Role updatedRole = roleService.updateRole(role, id);

        return ResponseMessage.ok(RoleResponseDTO.fromRole(updatedRole), "Role updated successfully");
    }

    // delete role
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);

        return ResponseMessage.ok(null,"Role deleted successfully");
    }
}
