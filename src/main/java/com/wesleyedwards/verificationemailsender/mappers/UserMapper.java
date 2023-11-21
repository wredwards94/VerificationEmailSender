package com.wesleyedwards.verificationemailsender.mappers;

import com.wesleyedwards.verificationemailsender.dtos.UserRequestDto;
import com.wesleyedwards.verificationemailsender.dtos.UserResponseDto;
import com.wesleyedwards.verificationemailsender.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto entityToDto(User user);

    User requestDtoToEntity(UserRequestDto userRequestDto);
}
