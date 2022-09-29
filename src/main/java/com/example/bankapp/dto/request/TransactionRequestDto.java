package com.example.bankapp.dto.request;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransactionRequestDto {
    private BigDecimal amount;
    private Long fromAccountId;
    private Long toAccountId;
    private String transactionReason;
}
