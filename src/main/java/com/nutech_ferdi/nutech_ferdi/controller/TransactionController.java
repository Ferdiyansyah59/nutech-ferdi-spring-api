package com.nutech_ferdi.nutech_ferdi.controller;

import com.nutech_ferdi.nutech_ferdi.dto.*;
import com.nutech_ferdi.nutech_ferdi.exception.ErrorResponse;
import com.nutech_ferdi.nutech_ferdi.model.TransactionHistory;
import com.nutech_ferdi.nutech_ferdi.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transServ;

    @PostMapping("/topup")
    public ResponseEntity<SuccessResponseDTO<TransactionTopUpResponseDTO>>
                userTopUp(@Valid @RequestBody TransactionToupUpRequestDTO requestDTO) {
        TransactionTopUpResponseDTO topup = transServ.userTopUp(requestDTO);

        SuccessResponseDTO<TransactionTopUpResponseDTO> res = new SuccessResponseDTO<>(
                0,
                "Top Up Balance berhasil",
                topup
        );

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/transaction")
    public ResponseEntity<SuccessResponseDTO<TransactionPaymentResponseDTO>>
                userPayment(@Valid @RequestBody TransactionPaymentRequestDTO requestDTO) {

        TransactionPaymentResponseDTO pay = transServ.userPayment(requestDTO);

        SuccessResponseDTO<TransactionPaymentResponseDTO> res = new SuccessResponseDTO<>(
                0,
                "Transaksi berhasil",
                pay
        );

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/transaction/history")
    public ResponseEntity<SuccessResponseDTO<TransactionHistoryResponseDTO>>
                userHistory(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                            @RequestParam(value = "limit", defaultValue = "10") @Min(1) Integer limit) {
        TransactionHistoryResponseDTO transHistory = transServ.userTransactionHistory(offset, limit);

        SuccessResponseDTO<TransactionHistoryResponseDTO> res = new SuccessResponseDTO<>(
                0,
                "Get History Berhasil",
                transHistory
        );

        return ResponseEntity.ok(res);
    }



    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse<Object> handleMalformedJson(HttpMessageNotReadableException ex) {
        return new ErrorResponse<>(
                102,
                "Paramter amount hanya boleh angka dan tidak boleh lebih kecil dari 0",
                null
        );
    }

}
