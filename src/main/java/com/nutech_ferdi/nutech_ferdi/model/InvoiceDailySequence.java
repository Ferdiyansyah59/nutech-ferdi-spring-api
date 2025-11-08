package com.nutech_ferdi.nutech_ferdi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoice_daily_sequences")
public class InvoiceDailySequence {
    @Id
    private LocalDate sequenceDate;

    @Column(nullable = false)
    private int lastSequence;
}
