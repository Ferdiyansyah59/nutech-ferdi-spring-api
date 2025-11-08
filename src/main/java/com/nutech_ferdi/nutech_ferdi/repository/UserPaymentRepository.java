package com.nutech_ferdi.nutech_ferdi.repository;

import com.nutech_ferdi.nutech_ferdi.model.UserPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPaymentRepository extends JpaRepository<UserPayment, Long> {
}
