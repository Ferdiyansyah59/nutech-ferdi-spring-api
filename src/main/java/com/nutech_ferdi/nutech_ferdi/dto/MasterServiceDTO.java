package com.nutech_ferdi.nutech_ferdi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterServiceDTO {
    private String service_code;
    private String service_name;
    private String service_icon;
    private int service_tariff;
}
