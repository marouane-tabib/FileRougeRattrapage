package com.colome.filerouge.modules.auth;

import com.colome.filerouge.entity.User;
import com.colome.filerouge.handlers.exceptionHandler.EmailAlreadyExistsException;
import com.colome.filerouge.handlers.exceptionHandler.InvalidEmailOrPasswordException;
import com.colome.filerouge.modules.auth.dto.AuthResponseDTO;
import com.colome.filerouge.modules.auth.dto.SignInRequestDTO;
import com.colome.filerouge.modules.auth.dto.SignupRequestDTO;
import com.colome.filerouge.modules.user.UserServiceInterface;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthServiceInterface {
    private final UserServiceInterface userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserServiceInterface userService, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponseDTO signup(SignupRequestDTO signupRequestDTO) throws EmailAlreadyExistsException {
        // check if user exists by email
        User user = userService.getUserByEmail(signupRequestDTO.getEmail());
        if (user != null) {
            // throw exception
            throw new EmailAlreadyExistsException("Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(signupRequestDTO.getPassword());
        signupRequestDTO.setPassword(encodedPassword);

        // convert the signup request to user
        User userSave = signupRequestDTO.toUser();

        // create user
        userSave = userService.addUser(userSave);

        //generate token
        String token = jwtService.generateToken(userSave);

        // return response
        return AuthResponseDTO.fromAuthResponseDTO(userSave, token);
    }

    @Override
    public AuthResponseDTO login(SignInRequestDTO signInRequestDTO) throws InvalidEmailOrPasswordException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequestDTO.getEmail(), signInRequestDTO.getPassword()));
        //check if user exists by email
        User user = userService.getUserByEmail(signInRequestDTO.getEmail());
        if (user != null) {
            // check if password matches
            if (passwordEncoder.matches(signInRequestDTO.getPassword(), user.getPassword())) {
                    // generate token
                    String token = jwtService.generateToken(user);
                    // return response
                    return AuthResponseDTO.fromAuthResponseDTO(user, token);
            }
        }
        throw new InvalidEmailOrPasswordException("Invalid email or password");
    }
}
