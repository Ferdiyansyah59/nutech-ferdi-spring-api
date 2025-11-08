package com.nutech_ferdi.nutech_ferdi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    @NotBlank(message = "Parameter email tidak boleh kosong")
    @Email(message = "Parameter email tidak sesuai format")
    private String email;

    @NotBlank(message = "Parameter nama depan tidak boleh kosong")
    private String first_name;

    @NotBlank(message = "Parameter nama belakang tidak boleh kosong")
    private String last_name;

    @NotBlank(message = "Parameter password tidak boleh kosong")
    @Size(min = 8, max = 100, message = "Panjang password harus 6 sampai 100 karakter")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_]).*$",
            message = "Password harus mengandung setidaknya satu huruf besar, satu huruf kecil, satu angka, dan satu karakter khusus"
    )
    private  String password;
}
