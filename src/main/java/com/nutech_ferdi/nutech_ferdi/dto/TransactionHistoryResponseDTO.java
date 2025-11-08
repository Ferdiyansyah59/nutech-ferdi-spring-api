package com.nutech_ferdi.nutech_ferdi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nutech_ferdi.nutech_ferdi.model.TransactionHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistoryResponseDTO {

    private Integer offset;
    private Integer limit;
    private List<TransactionHistory> records;
   /* @JsonProperty("invoice_number")
    private String invoiceNumber;

    @JsonProperty("transation_type")
    private String transactionType;

    @JsonProperty("description")
    private String description;

    @JsonProperty("total_amount")
    private int totalAmount;

    @JsonProperty("created_on")
    private LocalDateTime createdOn;*/
}
