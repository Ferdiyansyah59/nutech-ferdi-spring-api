package com.nutech_ferdi.nutech_ferdi.controller;

import com.nutech_ferdi.nutech_ferdi.dto.LoginRequestDTO;
import com.nutech_ferdi.nutech_ferdi.dto.LoginResponseDTO;
import com.nutech_ferdi.nutech_ferdi.dto.RegisterRequestDTO;
import com.nutech_ferdi.nutech_ferdi.dto.SuccessResponseDTO;
import com.nutech_ferdi.nutech_ferdi.model.User;
import com.nutech_ferdi.nutech_ferdi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<SuccessResponseDTO<LoginResponseDTO>>
                authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
        String token = authService.login(loginRequest);
        LoginResponseDTO loginResponse = new LoginResponseDTO(token);

        SuccessResponseDTO<LoginResponseDTO> res = new SuccessResponseDTO<>(
                0,
                "Login Sukses",
                loginResponse
        );

        return ResponseEntity.ok(res);
    }

    @PostMapping("/registration")
    public ResponseEntity<SuccessResponseDTO>
                registerUser(@Valid @RequestBody RegisterRequestDTO registerRequest) {
        User newUser = authService.register(registerRequest);

        SuccessResponseDTO res = new SuccessResponseDTO<>(
                0,
                "Registrasi berhasil silahkan login",
                null
        );

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
