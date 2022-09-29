package com.example.bankapp.service.impl;

import com.example.bankapp.model.Account;
import com.example.bankapp.model.Bank;
import com.example.bankapp.repository.BankRepository;
import com.example.bankapp.service.BankService;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BankServiceImpl implements BankService {
    private final BankRepository repository;

    @Override
    public Bank save(Bank bank) {
        return repository.save(bank);
    }

    @Override
    public BigDecimal getTotalTransactionFeeAmount(Long id) {
        return Optional.ofNullable(repository.getTotalTransactionFeeAmount(id))
                 .orElseGet(() -> new BigDecimal("0"));
    }

    @Override
    public BigDecimal getTotalTransferAmount(Long id) {
        return Optional.ofNullable(repository.getTotalTransferAmount(id))
                .orElseGet(() -> new BigDecimal("0"));
    }

    @Override
    public void addAccount(Long bankId, Account account) {
        Bank bank = repository.findById(bankId)
                .orElseThrow(() -> new NoSuchElementException("Bank not found by id: " + bankId));
        bank.getAccounts().add(account);
        repository.save(bank);
    }

    @Override
    public Bank findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bank not found by id: " + id));
    }
}
