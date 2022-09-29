package com.example.bankapp.dto.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class BankResponseDto {
    private Long id;
    private String nameBank;
    private BigDecimal flatFee;
    private BigDecimal percentFee;
    private BigDecimal totalTransactionFeeAmount;
    private BigDecimal totalTransferAmount;
}
