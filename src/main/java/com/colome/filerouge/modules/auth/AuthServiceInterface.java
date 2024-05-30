package com.colome.filerouge.modules.auth;

import com.colome.filerouge.handlers.exceptionHandler.EmailAlreadyExistsException;
import com.colome.filerouge.handlers.exceptionHandler.InvalidEmailOrPasswordException;
import com.colome.filerouge.modules.auth.dto.AuthResponseDTO;
import com.colome.filerouge.modules.auth.dto.SignInRequestDTO;
import com.colome.filerouge.modules.auth.dto.SignupRequestDTO;

public interface AuthServiceInterface {
    AuthResponseDTO signup(SignupRequestDTO signupRequestDTO) throws EmailAlreadyExistsException;
    AuthResponseDTO login(SignInRequestDTO signInRequestDTO) throws InvalidEmailOrPasswordException;
}
