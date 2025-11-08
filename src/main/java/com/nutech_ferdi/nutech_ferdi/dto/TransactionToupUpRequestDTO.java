package com.nutech_ferdi.nutech_ferdi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionToupUpRequestDTO {
    @JsonProperty("top_up_balance")
    @NotNull(message = "Parameter top_up_balance tidak boleh kosong")
    @Min(value = 1, message = "Paramter amount hanya boleh angka dan tidak boleh lebih kecil dari 0")
    private Integer topUpAmount;
}
