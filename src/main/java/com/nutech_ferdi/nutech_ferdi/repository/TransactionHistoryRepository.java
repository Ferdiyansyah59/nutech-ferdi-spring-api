package com.nutech_ferdi.nutech_ferdi.repository;

import com.nutech_ferdi.nutech_ferdi.model.TransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, String> {
    List<TransactionHistory> findByUserIdOrderByCreatedOnDesc(Integer userId);
    Page<TransactionHistory> findByUserId(Integer userId, Pageable pageable);
}
