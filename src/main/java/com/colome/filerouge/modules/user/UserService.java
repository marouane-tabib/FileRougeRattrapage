package com.colome.filerouge.modules.user;

import com.colome.filerouge.entity.Role;
import com.colome.filerouge.entity.User;
import com.colome.filerouge.handlers.exceptionHandler.OperationException;
import com.colome.filerouge.handlers.exceptionHandler.ResourceNotFoundException;
import com.colome.filerouge.modules.role.RoleService;
import com.colome.filerouge.modules.role.RoleServiceInterface;
import com.colome.filerouge.modules.user.dto.request.ChangePasswordRequestDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final RoleServiceInterface roleService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleServiceInterface roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User with id " + id + " not found")
                );
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        // check if user with email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new OperationException("User with email " + user.getEmail() + " already exists");
        }

        if (user.getRole() == null) {
            // set USER role if role is not provided
            Role role = roleService.getRoleByName("USER");
            user.setRole(role);
        } else {
            // get role by id
            Role role = roleService.getRoleById(user.getRole().getId());
            user.setRole(role);
        }

        // save user to database
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, Long id) {
        //get user by id
        User existingUser = getUserById(id);

        // check if user with email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent() && !user.getEmail().equals(existingUser.getEmail())) {
            throw new OperationException("email " + user.getEmail() + " already exists");
        }

        // set password to the user
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        existingUser.setPassword(user.getPassword());

        // save user to database
        return userRepository.save(existingUser);
    }

    // update profile
    @Override
    public User updateUserProfile(Long id, User user) {
        //get user by id
        User existingUser = getUserById(id);

        // check if user with email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent() && !user.getEmail().equals(existingUser.getEmail())) {
            throw new OperationException("email " + user.getEmail() + " already exists");
        }

        // set new data
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        // save user to database
        return userRepository.save(existingUser);
    }

    @Override
    public User updateUserPassword(ChangePasswordRequestDTO changePasswordRequestDTO, Long id) {
        //get user by id
        User existingUser = getUserById(id);

        // check if old password is correct
        if (!passwordEncoder.matches(changePasswordRequestDTO.getOldPassword(), existingUser.getPassword())) {
            throw new OperationException("Old password is incorrect");
        }

        // check if new password is the same as old password
        if (existingUser.getPassword().equals(changePasswordRequestDTO.getNewPassword())) {
            throw new OperationException("New password cannot be the same as old password");
        }

        // check if new password is the same as confirm password
        if (!changePasswordRequestDTO.getNewPassword().equals(changePasswordRequestDTO.getConfirmPassword())) {
            throw new OperationException("New password and confirm password do not match");
        }

        // set new password
        String encodedPassword = passwordEncoder.encode(changePasswordRequestDTO.getNewPassword());
        existingUser.setPassword(encodedPassword);

        // save user to database
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        // check if user exists
        getUserById(id);

        // delete user
        userRepository.deleteById(id);
    }
}
