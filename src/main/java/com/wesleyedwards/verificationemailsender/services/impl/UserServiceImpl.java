package com.wesleyedwards.verificationemailsender.services.impl;

import com.wesleyedwards.verificationemailsender.dtos.UserRequestDto;
import com.wesleyedwards.verificationemailsender.dtos.UserResponseDto;
import com.wesleyedwards.verificationemailsender.entities.Confirmation;
import com.wesleyedwards.verificationemailsender.entities.User;
import com.wesleyedwards.verificationemailsender.exceptions.AlreadyExistsException;
import com.wesleyedwards.verificationemailsender.mappers.UserMapper;
import com.wesleyedwards.verificationemailsender.repository.ConfirmationRepository;
import com.wesleyedwards.verificationemailsender.repository.UserRepository;
import com.wesleyedwards.verificationemailsender.services.EmailService;
import com.wesleyedwards.verificationemailsender.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;
    private final EmailService emailService;

    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        if (userRepository.existsByEmail(userRequestDto.getEmail())) throw new AlreadyExistsException("Email already exists");

        User newUser = userMapper.requestDtoToEntity(userRequestDto);
        newUser.setEnabled(false);
        userRepository.saveAndFlush(newUser);

        Confirmation confirmation = new Confirmation(newUser);
        confirmationRepository.saveAndFlush(confirmation);

        /* Send email to user with token */
        emailService.sendSimpleMailMessage(newUser.getName(), newUser.getEmail(), confirmation.getToken());

        return userMapper.entityToDto(newUser);
    }

    @Override
    public Boolean verifyToken(String token) {
        Optional<Confirmation> confirmationOptional = confirmationRepository.findByToken(token);
        if(confirmationOptional.isEmpty()) throw new RuntimeException("No token present for this user.");

        Confirmation confirmation = confirmationOptional.get();

        Optional<User> userOptional = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        if(userOptional.isEmpty()) throw new RuntimeException("This user does not exists.");

        User user = userOptional.get();
        user.setEnabled(true);
        userRepository.saveAndFlush(user);
        return Boolean.TRUE;
    }
}
