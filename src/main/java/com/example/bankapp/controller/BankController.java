package com.example.bankapp.controller;

import com.example.bankapp.dto.mapper.RequestDtoMapper;
import com.example.bankapp.dto.mapper.ResponseDtoMapper;
import com.example.bankapp.dto.request.BankRequestDto;
import com.example.bankapp.dto.response.AccountResponseDto;
import com.example.bankapp.dto.response.BankResponseDto;
import com.example.bankapp.model.Account;
import com.example.bankapp.model.Bank;
import com.example.bankapp.service.AccountService;
import com.example.bankapp.service.BankService;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bank")
public class BankController {
    private final BankService service;
    private final AccountService accountService;
    private final ResponseDtoMapper<AccountResponseDto, Account> accountResponseDtoMapper;
    private final ResponseDtoMapper<BankResponseDto, Bank> responseDtoMapper;
    private final RequestDtoMapper<BankRequestDto, Bank> requestDtoMapper;

    @PostMapping("/save")
    public BankResponseDto saveNewBank(@RequestBody BankRequestDto bank) {
        return responseDtoMapper.toDto(service.save(requestDtoMapper.toModel(bank)));
    }

    @PutMapping("{id}/add-account")
    public String addAccountToBank(@PathVariable Long id, @RequestParam Long accountId) {
        Account account = accountService.findById(accountId);
        service.addAccount(id, account);
        return "Account with id: " + accountId + " was added";
    }

    @GetMapping("/{id}/get-accounts")
    public List<AccountResponseDto> getAccountsByBankId(@PathVariable Long id) {
        return service.findById(id)
                .getAccounts()
                .stream()
                .map(accountResponseDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/get-total-fee-amount")
    public BigDecimal getTotalTransactionFeeAmount(@PathVariable Long id) {
        return service.getTotalTransactionFeeAmount(id);
    }

    @GetMapping("/{id}/get-total-transfer-amount")
    public BigDecimal getTotalTransferAmount(@PathVariable Long id) {
        return service.getTotalTransferAmount(id);
    }
}
