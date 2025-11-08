package com.nutech_ferdi.nutech_ferdi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Immutable
@Entity
@Table(name = "v_transaction_history")
public class TransactionHistory {
    @Id
    @JsonProperty(value = "invoice_number", index = 1)
    private String invoiceNumber;

    @JsonProperty(value = "transaction_type", index = 2)
    private String transactionType;


    @JsonProperty(value = "description", index = 3)
    private String description;

    @JsonProperty(value = "total_amount", index = 4)
    private Integer totalAmount;


    @JsonProperty(value = "created_on", index = 5)
    private LocalDateTime createdOn;

    @JsonIgnore
    private Integer userId;

}
