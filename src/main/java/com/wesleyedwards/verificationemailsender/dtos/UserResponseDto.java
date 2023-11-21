package com.wesleyedwards.verificationemailsender.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {
    private String name;

    private String email;

    private boolean isEnabled;
}
