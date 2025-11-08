package com.nutech_ferdi.nutech_ferdi.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginResponseDTO {
    private final String token;

}
