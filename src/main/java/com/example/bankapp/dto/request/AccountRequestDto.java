package com.example.bankapp.dto.request;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class AccountRequestDto {
    private String userName;
    private BigDecimal amount;
}
