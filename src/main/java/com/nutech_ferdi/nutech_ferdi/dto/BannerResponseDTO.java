package com.nutech_ferdi.nutech_ferdi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerResponseDTO {
    private String banner_name;
    private String banner_image;
    private String description;
}
