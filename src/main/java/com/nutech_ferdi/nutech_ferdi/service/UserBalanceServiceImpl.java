package com.nutech_ferdi.nutech_ferdi.service;

import com.nutech_ferdi.nutech_ferdi.dto.UserBalanceResponseDTO;
import com.nutech_ferdi.nutech_ferdi.exception.ResourceNotFoundException;
import com.nutech_ferdi.nutech_ferdi.model.UserBalance;
import com.nutech_ferdi.nutech_ferdi.repository.UserBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBalanceServiceImpl implements UserBalanceService {

    private final UserBalanceRepository balanceRepo;

    private UserBalanceResponseDTO mapResponseBalance(UserBalance balance) {
        UserBalanceResponseDTO res = new UserBalanceResponseDTO();
        res.setBalance(balance.getBalanceAmount());
        return res;
    }

    @Override
    public UserBalanceResponseDTO getUserBalance() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String email = auth.getName();

        UserBalance balance = balanceRepo.findByUserEmail(email)
                .orElseThrow(()
                    -> new ResourceNotFoundException("User balance not found with email " + email)
                );

        return mapResponseBalance(balance);
    }
}
