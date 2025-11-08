package com.nutech_ferdi.nutech_ferdi.controller;

import com.nutech_ferdi.nutech_ferdi.dto.SuccessResponseDTO;
import com.nutech_ferdi.nutech_ferdi.dto.UserBalanceResponseDTO;
import com.nutech_ferdi.nutech_ferdi.service.UserBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserBalanceController {
    private final UserBalanceService balanceService;
    @GetMapping("/balance")
    public ResponseEntity<SuccessResponseDTO<UserBalanceResponseDTO>> getUserBalance() {
        UserBalanceResponseDTO balance = balanceService.getUserBalance();

        SuccessResponseDTO<UserBalanceResponseDTO> res = new SuccessResponseDTO<>(
                0,
                "Get Balance Berhasil",
                balance
        );

        return ResponseEntity.ok(res);
    }
}
