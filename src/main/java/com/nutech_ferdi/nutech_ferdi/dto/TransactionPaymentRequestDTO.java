package com.nutech_ferdi.nutech_ferdi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionPaymentRequestDTO {
    @JsonProperty("service_code")
    @NotBlank(message = "Parameter service_code tidak boleh kosong")
    private String serviceCode;
}
