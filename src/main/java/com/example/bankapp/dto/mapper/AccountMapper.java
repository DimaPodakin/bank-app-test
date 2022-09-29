package com.example.bankapp.dto.mapper;

import com.example.bankapp.dto.request.AccountRequestDto;
import com.example.bankapp.dto.response.AccountResponseDto;
import com.example.bankapp.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper implements ResponseDtoMapper<AccountResponseDto, Account>,
        RequestDtoMapper<AccountRequestDto, Account> {
    @Override
    public Account toModel(AccountRequestDto dto) {
        Account account = new Account();
        account.setUserName(dto.getUserName());
        account.setAmount(dto.getAmount());
        return account;
    }

    @Override
    public AccountResponseDto toDto(Account account) {
        AccountResponseDto dto = new AccountResponseDto();
        dto.setId(account.getId());
        dto.setUserName(account.getUserName());
        dto.setAmount(account.getAmount());
        return dto;
    }
}
