package com.nutech_ferdi.nutech_ferdi.repository;

import com.nutech_ferdi.nutech_ferdi.dto.BalanceInfoDTO;
import com.nutech_ferdi.nutech_ferdi.model.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalance, Long> {
    Optional<UserBalance> findByUserEmail(String email);

    @Query("SELECT new com.nutech_ferdi.nutech_ferdi.dto.BalanceInfoDTO(ub.id, ub.balanceAmount) " +
            "FROM UserBalance ub WHERE ub.user.email = :email")
    Optional<BalanceInfoDTO> findIdAndBalanceAmountByUserEmail(@Param("email") String email);


    @Modifying
    @Query("UPDATE UserBalance ub SET ub.balanceAmount = :newBalance WHERE ub.id = :balanceId")
    void updateBalanceById(@Param("balanceId") int id, @Param("newBalance") int balance);
}
