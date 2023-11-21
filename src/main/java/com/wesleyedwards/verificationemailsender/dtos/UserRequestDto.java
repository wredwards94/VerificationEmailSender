package com.wesleyedwards.verificationemailsender.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
public class UserRequestDto {
    private String name;

    private String email;

    private String password;
}
