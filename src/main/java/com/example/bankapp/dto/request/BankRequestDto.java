package com.example.bankapp.dto.request;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class BankRequestDto {
    private String nameBank;
    private BigDecimal flatFee;
    private BigDecimal percentFee;
    private BigDecimal totalTransactionFeeAmount;
    private BigDecimal totalTransferAmount;
}
