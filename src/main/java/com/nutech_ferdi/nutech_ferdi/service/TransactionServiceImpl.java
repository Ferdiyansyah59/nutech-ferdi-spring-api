package com.nutech_ferdi.nutech_ferdi.service;

import com.nutech_ferdi.nutech_ferdi.dto.*;
import com.nutech_ferdi.nutech_ferdi.exception.ResourceNotFoundException;
import com.nutech_ferdi.nutech_ferdi.model.*;
import com.nutech_ferdi.nutech_ferdi.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    private final UserBalanceRepository balanceRepo;
    private final UserRepository userRepo;
    private final MasterServiceRepository serviceRepo;
    private final InvoiceDailySequenceRepository sequenceRepo;
    private final UserTopupRepository topupRepo;
    private final UserPaymentRepository payRepo;
    private final TransactionHistoryRepository historyRepo;

    private String generateInvoice() {
        Long nextSequence = sequenceRepo.getNextSequenceForToday();
        LocalDate today = LocalDate.now();
        String datePart = today.format(DateTimeFormatter.ofPattern("ddMMyyyy"));

        return String.format("INV%s_%03d", datePart, nextSequence);
    }


    @Transactional
    @Override
    public TransactionTopUpResponseDTO userTopUp(TransactionToupUpRequestDTO requestDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String email = auth.getName();

        User currentUser = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email " + email));

        UserBalance balanceData = balanceRepo.findByUserEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Balance not found with email " + email));

        int topUpAmount = requestDto.getTopUpAmount();

        int balanceId = balanceData.getId();
        int userBalance = balanceData.getBalanceAmount();
        int newBalance = userBalance + topUpAmount;

        balanceRepo.updateBalanceById(balanceId, newBalance);
        String invoiceNumber = generateInvoice();

        UserTopup userTopup = new UserTopup();
        userTopup.setUser(currentUser);
        userTopup.setInvoiceNumber(invoiceNumber);
        userTopup.setAmount(topUpAmount);
        topupRepo.save(userTopup);


        TransactionTopUpResponseDTO res = new TransactionTopUpResponseDTO();

        res.setBalance(newBalance);


        return res;
    }

    private TransactionPaymentResponseDTO mapPayment(UserPayment up){
        TransactionPaymentResponseDTO res = new TransactionPaymentResponseDTO();
        res.setInvoiceNumber(up.getInvoiceNumber());
        res.setServiceCode(up.getService().getServiceCode());
        res.setServiceName(up.getService().getServiceName());
        res.setTransactionType("PAYMENT");
        res.setTotalAmount(up.getAmount());
        res.setCreatedOn(up.getCreatedAt());

        return res;
    }

    @Transactional
    @Override
    public TransactionPaymentResponseDTO userPayment(TransactionPaymentRequestDTO requestDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String email = auth.getName();

        User currentUser = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email " + email));

        UserBalance balanceData = balanceRepo.findByUserEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Balance not found with email " + email));

        String servCode = requestDTO.getServiceCode();
        MasterService serv = serviceRepo.findByServiceCode(servCode)
                .orElseThrow(() -> new ResourceNotFoundException("Service atau Layanan tidak ditemukan"));


        int balanceId = balanceData.getId();
        int userBalance = balanceData.getBalanceAmount();
        int serviceTariff = serv.getServiceTariff();

        if (userBalance < serviceTariff) {
            throw new IllegalArgumentException("Saldo tidak mencukupi untuk melakukan transaksi service " + serv.getServiceName());
        }

        int newBalance = userBalance - serviceTariff;

        balanceRepo.updateBalanceById(balanceId, newBalance);
        String invoiceNumber = generateInvoice();

        UserPayment userPayment = new UserPayment();
        userPayment.setUser(currentUser);
        userPayment.setService(serv);
        userPayment.setAmount(serviceTariff);
        userPayment.setInvoiceNumber(invoiceNumber);
        UserPayment savedUserPayment = payRepo.save(userPayment);


        return mapPayment(savedUserPayment);
    }

    @Override
    public TransactionHistoryResponseDTO userTransactionHistory(Integer offset, Integer limit) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepo.findByEmail(email).orElseThrow(()
                -> new ResourceNotFoundException("Email not found with email " + email));

        int userId = user.getId();

        int pageNumber = offset / limit;
        int pageSize = limit;

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "createdOn"));

        Page<TransactionHistory> historyPage = historyRepo.findByUserId(userId, pageable);

        TransactionHistoryResponseDTO res = new TransactionHistoryResponseDTO();
        res.setOffset(offset);
        res.setLimit(limit);
        res.setRecords(historyPage.getContent());

        return res;
    }
}
