package com.nutech_ferdi.nutech_ferdi.repository;

import com.nutech_ferdi.nutech_ferdi.model.InvoiceDailySequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InvoiceDailySequenceRepository extends JpaRepository<InvoiceDailySequence, LocalDate> {
    @Query(value =
            "INSERT INTO invoice_daily_sequences (sequence_date, last_sequence) " +
                    "VALUES (CURRENT_DATE, 1) " +
                    "ON CONFLICT (sequence_date) " +
                    "DO UPDATE SET last_sequence = invoice_daily_sequences.last_sequence + 1 " +
                    "RETURNING last_sequence",
            nativeQuery = true)
    long getNextSequenceForToday();
}
