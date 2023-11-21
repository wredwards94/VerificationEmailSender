package com.wesleyedwards.verificationemailsender.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6686182686102588228L;

    private String message;
}
