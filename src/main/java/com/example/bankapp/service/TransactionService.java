package com.example.bankapp.service;

import com.example.bankapp.model.Transaction;
import java.util.List;

public interface TransactionService {
    String transactionByFlatFee(Transaction transaction, Long bankId);

    String transactionByPercentFee(Transaction transaction, Long bankId);

    String withdrawalFromAccount(Transaction transaction);

    String depositToAccount(Transaction transaction);

    List<Transaction> getHistory(Long accountId);
}
