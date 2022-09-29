package com.example.bankapp.dto.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransactionResponseDto {
    private Long id;
    private BigDecimal amount;
    private Long fromAccountId;
    private Long toAccountId;
    private String transactionReason;
}
