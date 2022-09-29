package com.example.bankapp.service;

import com.example.bankapp.model.Account;
import com.example.bankapp.model.Bank;
import java.math.BigDecimal;

public interface BankService {
    Bank save(Bank bank);

    BigDecimal getTotalTransactionFeeAmount(Long id);

    BigDecimal getTotalTransferAmount(Long id);

    void addAccount(Long bankId, Account account);

    Bank findById(Long id);
}
