package com.wesleyedwards.verificationemailsender.services;

import com.wesleyedwards.verificationemailsender.dtos.UserRequestDto;
import com.wesleyedwards.verificationemailsender.dtos.UserResponseDto;

public interface UserService {
    UserResponseDto saveUser(UserRequestDto userRequestDto);

    Boolean verifyToken(String token);
}
