package com.nutech_ferdi.nutech_ferdi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "master_services", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"service_code"}),
        @UniqueConstraint(columnNames = {"service_name"})
})
public class MasterService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @Column(name = "service_code", nullable = false, unique = true)
    private String serviceCode;

    @Column(name = "service_name", nullable = false, unique = true)
    private String serviceName;

    @Column(name = "service_icon", nullable = false)
    private String serviceIcon;

    @Column(name = "service_tariff", nullable = false)
    private int serviceTariff;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "service")
    private List<UserPayment> userPayments;

}
