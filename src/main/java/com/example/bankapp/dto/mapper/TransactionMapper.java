package com.example.bankapp.dto.mapper;

import com.example.bankapp.dto.request.TransactionRequestDto;
import com.example.bankapp.dto.response.TransactionResponseDto;
import com.example.bankapp.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper implements ResponseDtoMapper<TransactionResponseDto, Transaction>,
        RequestDtoMapper<TransactionRequestDto, Transaction> {
    @Override
    public Transaction toModel(TransactionRequestDto dto) {
        Transaction transaction = new Transaction();
        transaction.setAmount(dto.getAmount());
        transaction.setFromAccountId(dto.getFromAccountId());
        transaction.setToAccountId(dto.getToAccountId());
        transaction.setTransactionReason(dto.getTransactionReason());
        return transaction;
    }

    @Override
    public TransactionResponseDto toDto(Transaction transaction) {
        TransactionResponseDto dto = new TransactionResponseDto();
        dto.setId(transaction.getId());
        dto.setAmount(transaction.getAmount());
        dto.setFromAccountId(transaction.getFromAccountId());
        dto.setToAccountId(transaction.getToAccountId());
        dto.setTransactionReason(transaction.getTransactionReason());
        return dto;
    }
}
