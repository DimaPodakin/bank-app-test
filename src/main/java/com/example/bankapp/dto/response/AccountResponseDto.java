package com.example.bankapp.dto.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class AccountResponseDto {
    private Long id;
    private String userName;
    private BigDecimal amount;
}
