package com.wesleyedwards.verificationemailsender.controller;

import com.wesleyedwards.verificationemailsender.dtos.UserRequestDto;
import com.wesleyedwards.verificationemailsender.dtos.UserResponseDto;
import com.wesleyedwards.verificationemailsender.entities.HttpResponse;
import com.wesleyedwards.verificationemailsender.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<HttpResponse> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto user = userService.saveUser(userRequestDto);
        return ResponseEntity.created(URI.create("")).body(HttpResponse.builder()
                .timeStamp(LocalDateTime.now().toString()).data(Map.of("user", user))
                .message("User created").status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build()
        );
    }

    @GetMapping
    public ResponseEntity<HttpResponse> confirmUserAccount(@RequestParam("token") String token) {
        Boolean isSuccess = userService.verifyToken(token);
        return ResponseEntity.ok().body(HttpResponse.builder()
                .timeStamp(LocalDateTime.now().toString()).data(Map.of("Success", isSuccess))
                .message("Account Verified").status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }
}
