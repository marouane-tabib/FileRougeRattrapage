package com.colome.filerouge.modules.auth;

import com.colome.filerouge.handlers.exceptionHandler.EmailAlreadyExistsException;
import com.colome.filerouge.handlers.exceptionHandler.InvalidEmailOrPasswordException;
import com.colome.filerouge.handlers.response.ResponseMessage;
import com.colome.filerouge.modules.auth.dto.SignInRequestDTO;
import com.colome.filerouge.modules.auth.dto.SignupRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthServiceInterface authenticationService;

    public AuthController(AuthServiceInterface authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequestDTO signupRequestDTO){
        try {
            return ResponseMessage.ok(authenticationService.signup(signupRequestDTO), "User created successfully");
        } catch (EmailAlreadyExistsException e) {
            return ResponseMessage.badRequest(e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody SignInRequestDTO signupRequestDTO){
        try {
            return ResponseMessage.ok(authenticationService.login(signupRequestDTO), "Login successful");
        } catch (InvalidEmailOrPasswordException e) {
            return ResponseMessage.badRequest(e.getMessage());
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile(){
        List<SimpleGrantedAuthority> user = (List<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return ResponseEntity.ok(user);
    }
}
