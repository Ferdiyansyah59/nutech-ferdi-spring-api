package com.nutech_ferdi.nutech_ferdi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponseDTO {
    private String email;
    private String first_name;
    private String last_name;
    private String profile_image;
}
