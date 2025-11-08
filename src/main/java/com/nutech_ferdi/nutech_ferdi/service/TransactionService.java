package com.nutech_ferdi.nutech_ferdi.service;

import com.nutech_ferdi.nutech_ferdi.dto.*;

public interface TransactionService {
    TransactionTopUpResponseDTO userTopUp(TransactionToupUpRequestDTO requestDto);

    TransactionPaymentResponseDTO userPayment(TransactionPaymentRequestDTO requestDTO);

    TransactionHistoryResponseDTO userTransactionHistory(Integer offset, Integer limit);
}
