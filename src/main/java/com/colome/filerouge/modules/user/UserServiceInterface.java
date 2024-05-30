package com.colome.filerouge.modules.user;

import com.colome.filerouge.entity.User;
import com.colome.filerouge.modules.user.dto.request.ChangePasswordRequestDTO;

import java.util.List;

public interface UserServiceInterface {
    User getUserById(Long id);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    User addUser(User user);
    User updateUser(User user, Long id);
    // update profile
    User updateUserProfile(Long id, User user);
    // change password
    User updateUserPassword(ChangePasswordRequestDTO changePasswordRequestDTO, Long id);
    void deleteUser(Long id);
}
