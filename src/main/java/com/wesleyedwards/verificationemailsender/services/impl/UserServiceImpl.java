package com.wesleyedwards.verificationemailsender.services.impl;

import com.wesleyedwards.verificationemailsender.dtos.UserRequestDto;
import com.wesleyedwards.verificationemailsender.dtos.UserResponseDto;
import com.wesleyedwards.verificationemailsender.entities.User;
import com.wesleyedwards.verificationemailsender.exceptions.AlreadyExistsException;
import com.wesleyedwards.verificationemailsender.mappers.UserMapper;
import com.wesleyedwards.verificationemailsender.repository.ConfirmationRepository;
import com.wesleyedwards.verificationemailsender.repository.UserRepository;
import com.wesleyedwards.verificationemailsender.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;

    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new AlreadyExistsException("Email already exists");
        }
        User newUser = userMapper.requestDtoToEntity(userRequestDto);
        newUser.setEnabled(false);

        return userMapper.entityToDto(userRepository.saveAndFlush(newUser));
    }

    @Override
    public Boolean verifyToken(String token) {
        return null;
    }
}
