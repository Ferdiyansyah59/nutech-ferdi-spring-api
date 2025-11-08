package com.nutech_ferdi.nutech_ferdi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    @NotBlank(message = "Parameter email tidak boleh kosong")
    @Email(message = "Parameter email tidak sesuai format")
    private String email;

    @NotBlank(message = "Parameter Password tidak boleh kosong")
    private String password;

}
